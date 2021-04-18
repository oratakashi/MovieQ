package com.oratakashi.movieq.ui.trailer

import android.R.id
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.movieq.data.model.trailer.DataTrailer
import com.oratakashi.movieq.databinding.ActivityTrailerBinding
import com.oratakashi.movieq.utils.hideSystemUI
import com.oratakashi.movieq.utils.showSystemUI
import com.oratakashi.viewbinding.core.tools.*
import com.oratakashi.viewbinding.core.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener


class TrailerActivity : AppCompatActivity() {

    private var isShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            hideSystemUI()
            showSystemUI()

            tvTitle.text = data.name

            ytPlayer.also {
                lifecycle.addObserver(it)
                it.initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(data.key!!, 0F)
                            youTubePlayer.play()
                        }
                    }, true
                )
                it.addFullScreenListener(object : YouTubePlayerFullScreenListener {
                    override fun onYouTubePlayerEnterFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        ivBack.gone()
                        this@TrailerActivity.hideSystemUI()
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        ivBack.visible()
                        this@TrailerActivity.showSystemUI()
                    }
                })
                it.enableBackgroundPlayback(true)

            }
            tvYouTube.onClick {
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${data.key}"))
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${data.key}")
                )
                try {
                    startActivity(appIntent)
                } catch (ex: ActivityNotFoundException) {
                    startActivity(webIntent)
                }
            }
        }
    }

    private val data : DataTrailer by intent("data")
    private val binding : ActivityTrailerBinding by viewBinding()
}