package com.jem.weplayer.util.network.get

import com.jem.weplayer.data.YoutubeData
import com.jem.weplayer.data.YoutubePageInfo

data class GetYoutubeSearchResponse (
    var kind : String,
    var etag : String,
    var nextPageToken : String,
    var regionCode : String,
    var pageInfo : YoutubePageInfo,
    var items : ArrayList<YoutubeData>
)