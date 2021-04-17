package com.oratakashi.movieq.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oratakashi.movieq.databinding.ActivitySplashBinding
import com.oratakashi.movieq.ui.genre.GenreActivity
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.viewBinding

class SplashActivity : AppCompatActivity() {

    private val binding : ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            lottie.addAnimatorUpdateListener {
                if(it.animatedFraction == 1.0f){
                    startActivity(GenreActivity::class.java)
                    finish()
                }
            }
        }
    }
}