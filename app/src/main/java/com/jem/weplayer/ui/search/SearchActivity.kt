package com.jem.weplayer.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jem.weplayer.R
import com.jem.weplayer.data.YoutubeData
import com.jem.weplayer.ui.main.MainActivity
import com.jem.weplayer.ui.search.adapter.SearchAdapter
import com.jem.weplayer.util.RecyclerviewItemDeco
import com.jem.weplayer.util.network.YoutubeApiClient
import com.jem.weplayer.util.network.YoutubeNetworkService
import com.jem.weplayer.util.network.get.GetYoutubeSearchResponse
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Response

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    val apiKey = "AIzaSyBeSiytACOFX_7jatNrWslp1OXKUDit-Yk"
    val TAG = "SearchActivity"

    lateinit var searchAdapter : SearchAdapter
    lateinit var youtubeDatas : ArrayList<YoutubeData>
    lateinit var recyclerviewItemDeco: RecyclerviewItemDeco
    lateinit var youtubeNetworkService : YoutubeNetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerInit()

        btn_keyword_search.setOnClickListener{
            getDatas(et_keyword_search.text.toString())
        }
    }

    // 유튜브 검색 데이터 가져오기
    fun getDatas(keyword : String) {
        youtubeNetworkService = YoutubeApiClient.getRetrofit().create(YoutubeNetworkService::class.java)
//        val getVideoListResponse = youtubeNetworkService.getYoutubeSearch(apiKey, "snippet", keyword, 30)
        val getVideoListResponse = youtubeNetworkService.getYoutubeSearch(apiKey, "snippet", "아이유", 30)

        getVideoListResponse.enqueue(object : retrofit2.Callback<GetYoutubeSearchResponse>{
            override fun onFailure(call: Call<GetYoutubeSearchResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<GetYoutubeSearchResponse>,
                response: Response<GetYoutubeSearchResponse>
            ) {
                if(response.isSuccessful){
                    response.body().let {
                        Log.v(TAG, "바디 값 = " + response.body())
                        youtubeDatas = response.body()!!.items

                        searchAdapter.setDatas(youtubeDatas)
                    }
                }
                else{
                    Log.v(TAG, "에러 메시지 = " + response.errorBody())
                }
            }

        })
    }

    // recyclerview 초기화
    fun recyclerInit(){

        searchAdapter = SearchAdapter(this)
        recyclerviewItemDeco =
            RecyclerviewItemDeco(applicationContext)
        searchAdapter.setOnItemClickListener(this)
//        searchAdapter.setOnLongClickListener(this)

        rv_list_search.adapter = searchAdapter
        rv_list_search.addItemDecoration(recyclerviewItemDeco)
        rv_list_search.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onClick(v: View) {

        var position : Int = rv_list_search.getChildAdapterPosition(v)
        var videoId : String = youtubeDatas[position].id.videoId

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("videoId", videoId)
        startActivity(intent)
    }
}
