package com.oratakashi.movieq.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.model.movie.ResponseMovie
import com.oratakashi.movieq.databinding.ActivityReviewBinding
import com.oratakashi.viewbinding.core.*
import com.oratakashi.viewbinding.core.tools.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            ivBack.onClick { onBackPressed() }
            tvTitle.text = data.title
            tvRatting.text = when(data.vote_average != null){
                true    -> (data.vote_average!! / 2f).toString()
                false   -> "0"
            }
            rvReview.also {
                it.layoutManager = LinearLayoutManager(this@ReviewActivity)
                it.adapter = adapter
                it.addItemDecoration(DividerItemDecoration(this@ReviewActivity, DividerItemDecoration.VERTICAL))
            }

            srReview.setOnRefreshListener {
                srReview.isRefreshing = false
                viewModel.getReview(data.id!!, this@ReviewActivity)
            }

            viewModel.state.observe(this@ReviewActivity){
                when(it){
                    is ReviewState.Loading  -> {
                        lottie.setAnimation(R.raw.loading)
                        lottie.playAnimation()
                        lottie.visible()
                        rvReview.gone()
                    }
                    is ReviewState.Result   -> {
                        rvReview.visible()
                        lottie.gone()
                    }
                    is ReviewState.Error    -> {
                        lottie.setAnimation(R.raw.error)
                        lottie.playAnimation()
                        lottie.visible()
                        rvReview.gone()
                        if(it.error.message != null) toast(it.error.message!!)
                    }
                }
            }
            viewModel.data.observe(this@ReviewActivity, adapter::submitList)
            viewModel.getReview(data.id!!, this@ReviewActivity)
        }
    }

    private val adapter : ReviewAdapter by lazy {
        ReviewAdapter()
    }

    private val viewModel : ReviewViewModel by viewModels()
    private val binding : ActivityReviewBinding by viewBinding()
    private val data : ResponseMovie by intent("data")
}