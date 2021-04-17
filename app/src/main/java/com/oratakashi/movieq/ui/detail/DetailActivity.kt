package com.oratakashi.movieq.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.databinding.ActivityDetailBinding
import com.oratakashi.viewbinding.core.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        } else {
            window.setDecorFitsSystemWindows(false)
        }
        with(binding){
            ivBackground.load(BuildConfig.IMAGE_URL + data.backdrop_path)
            ivImage.load(BuildConfig.IMAGE_URL + data.poster_path){
                transformations(RoundedCornersTransformation(25f))
            }
        }
    }

    private val binding : ActivityDetailBinding by viewBinding()
    private val data : DataDiscover by intent("data")
}