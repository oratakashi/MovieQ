package com.oratakashi.movieq.ui.genre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.oratakashi.movieq.R
import com.oratakashi.movieq.databinding.ActivityGenreBinding
import com.oratakashi.movieq.ui.main.MainActivity
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.tools.visible
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreActivity : AppCompatActivity() {

    private val adapter : GenreAdapter by lazy {
        GenreAdapter { data ->
            startActivity(MainActivity::class.java) {
                it.putExtra("data", data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            rvGenre.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(applicationContext, 2)
            }
            viewModel.state.observe(this@GenreActivity){
                when(it){
                    is GenreState.Loading -> {
                        lottie.visible()
                        lottie.setAnimation(R.raw.loading)
                        lottie.playAnimation()
                        rvGenre.gone()
                    }
                    is GenreState.Result -> {
                        adapter.submitList(it.data.genres)
                        rvGenre.visible()
                        lottie.gone()
                    }
                    is GenreState.Error   -> {
                        rvGenre.gone()
                        lottie.setAnimation(R.raw.error)
                        lottie.playAnimation()
                        lottie.visible()
                        if(it.error.message != null) toast(it.error.message!!)
                    }
                }
            }
            viewModel.getGenre()
        }
    }

    private val binding: ActivityGenreBinding by viewBinding()
    private val viewModel : GenreViewModel by viewModels()
}