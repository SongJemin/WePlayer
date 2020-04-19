package com.jem.weplayer.ui.main

import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.jem.weplayer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : YouTubeBaseActivity() {

    val TAG = "MainActivity"
    val apiKey = "AIzaSyBeSiytACOFX_7jatNrWslp1OXKUDit-Yk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pauseTime : Long = 0
        var replayTime : Long = 0
        var pauseFlag : Boolean = false

        ypv_content_main.initialize(apiKey, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                wasRestored: Boolean
            ) {

                var videoId = intent.getStringExtra("videoId")

                // 1000 = 1초
                btn_time_search.setOnClickListener {
                    player!!.loadVideo(videoId, Integer.parseInt(et_time_search.text.toString()))
                }

                if(!wasRestored){
                    player!!.loadVideo(videoId)
                }

                player!!.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
                    override fun onAdStarted() {
                    }

                    override fun onLoading() {
                    }

                    override fun onVideoStarted() {
                    }

                    override fun onLoaded(p0: String?) {
                        player.play()
                    }

                    override fun onVideoEnded() {
                    }

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {
                    }

                })

                player.setPlaybackEventListener(object : YouTubePlayer.PlaybackEventListener{
                    override fun onSeekTo(p0: Int) {
                    }

                    override fun onBuffering(p0: Boolean) {
                    }

                    override fun onPlaying() {
                        if(pauseFlag){
                            replayTime = System.currentTimeMillis()
                            var subTime : Int = (replayTime - pauseTime).toInt()
                            player.seekRelativeMillis(subTime)
                            pauseFlag = false
                        }
                    }

                    override fun onStopped() {
                    }

                    override fun onPaused() {
                        if(!pauseFlag){
                            pauseFlag = true
                            pauseTime = System.currentTimeMillis()
                        }
                    }
                })
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {
            }

        })
    }
}
