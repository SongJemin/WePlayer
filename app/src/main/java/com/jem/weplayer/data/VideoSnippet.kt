package com.jem.weplayer.data

data class VideoSnippet (
    var publishedAt : String,
    var channelId : String,
    var title : String,
    var description : String,
    var thumbnails : VideoThumnail,
    var channelTitle : String,
    var liveBroadcastContent : String
)