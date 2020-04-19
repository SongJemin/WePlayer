package com.jem.weplayer.util.network

import com.google.gson.JsonObject
import com.jem.weplayer.util.network.get.GetYoutubeSearchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Multipart


interface YoutubeNetworkService {

    ////////////////////* GET *///////////////////////////
    // 유튜브 리스트 검색
    @GET("search")
    fun getYoutubeSearch(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("q") q : String,
        @Query("maxResults") maxResults : Int
    ) : Call<GetYoutubeSearchResponse>
}