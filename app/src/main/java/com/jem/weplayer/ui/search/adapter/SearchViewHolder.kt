package com.jem.weplayer.ui.search.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jem.weplayer.R

class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var videoThumnailImg : ImageView = itemView.findViewById(R.id.iv_thumnail_item_search)
    var videoTitle : TextView = itemView.findViewById(R.id.tv_title_item_search)
}