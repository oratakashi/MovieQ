package com.oratakashi.movieq.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.genre.DataGenre
import com.oratakashi.movieq.databinding.ActivityMainBinding
import com.oratakashi.movieq.ui.detail.DetailActivity
import com.oratakashi.viewbinding.core.*
import com.oratakashi.viewbinding.core.tools.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val adapter : DiscoverAdapter by lazy {
        DiscoverAdapter { data ->
            startActivity(DetailActivity::class.java){
                it.putExtra("data", data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            ivBack.onClick { onBackPressed() }
            tvTitle.text = genre.name
            tvSubTitle.text = String.format(
                getString(R.string.placeholder_sub_genre),
                genre.name
            )
            rvDiscover.also {
                it.adapter = adapter
                it.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }

            srDiscover.setOnRefreshListener {
                srDiscover.isRefreshing = false
                viewModel.getDiscover(genre.id, this@MainActivity)
            }

            viewModel.state.observe(this@MainActivity){
                when(it){
                    is DiscoverState.Loading   -> {
                        lottie.setAnimation(R.raw.loading)
                        lottie.playAnimation()
                        lottie.visible()
                        rvDiscover.gone()
                    }
                    is DiscoverState.Result    -> {
                        rvDiscover.visible()
                        lottie.gone()
                    }
                    is DiscoverState.Error      -> {
                        lottie.setAnimation(R.raw.error)
                        lottie.playAnimation()
                        lottie.visible()
                        rvDiscover.gone()
                        if(it.error.message != null) toast(it.error.message!!)
                    }
                }
            }
            viewModel.data.observe(this@MainActivity, adapter::submitList)
            viewModel.getDiscover(genre.id, this@MainActivity)
        }
    }

    private val viewModel: MainViewModel by viewModels()
    private val binding : ActivityMainBinding by viewBinding()
    private val genre : DataGenre by intent("data")
}