package com.jem.weplayer.data

data class YoutubeData (
    var kind : String,
    var etag : String,
    var id : VideoID,
    var snippet : VideoSnippet
)