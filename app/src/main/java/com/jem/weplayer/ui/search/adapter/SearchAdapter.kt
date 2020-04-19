package com.jem.weplayer.ui.search.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jem.weplayer.R
import com.jem.weplayer.data.YoutubeData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SearchAdapter internal constructor(context: Context) : RecyclerView.Adapter<SearchViewHolder>() {

    private lateinit var onItemClick : View.OnClickListener
    private lateinit var onLongItemClick : View.OnLongClickListener
    private var youtubeDatas = emptyList<YoutubeData>() // Cached copy of words
    private val context = context

    fun setOnItemClickListener(l : View.OnClickListener) {
        onItemClick = l
    }

    fun setOnLongClickListener(l : View.OnLongClickListener){
        onLongItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)

        view.setOnClickListener(onItemClick)
//        view.setOnLongClickListener(onLongItemClick)

        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = youtubeDatas.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(context).load(youtubeDatas.get(position).snippet.thumbnails.default.url).centerCrop().into(holder.videoThumnailImg)
        holder.videoTitle.text = youtubeDatas.get(position).snippet.channelTitle
    }

    internal fun setDatas(youtubeDatas: ArrayList<YoutubeData>) {
        this.youtubeDatas = youtubeDatas
        notifyDataSetChanged()
    }
}