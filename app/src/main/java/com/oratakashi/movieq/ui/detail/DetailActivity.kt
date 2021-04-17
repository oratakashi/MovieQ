package com.oratakashi.movieq.ui.detail

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar
import com.oratakashi.movieq.BuildConfig
import com.oratakashi.movieq.R
import com.oratakashi.movieq.data.`object`.DetailMovieObject
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.databinding.ActivityDetailBinding
import com.oratakashi.movieq.utils.dateFormat
import com.oratakashi.viewbinding.core.*
import com.oratakashi.viewbinding.core.tools.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            setupToolbar()

            rvGenre.also {
                it.adapter = genre
                it.layoutManager = LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }

            rvCast.also {
                it.adapter = cast
                it.layoutManager = GridLayoutManager(
                    this@DetailActivity,
                    1,
                    GridLayoutManager.HORIZONTAL,
                    false
                )
            }

            rvVideo.also {
                it.adapter = trailer
                it.layoutManager = GridLayoutManager(
                    this@DetailActivity,
                    1,
                    GridLayoutManager.HORIZONTAL,
                    false
                )
            }

            viewModel.state.observe(this@DetailActivity){
                when(it){
                    is DetailState.Loading -> {
                        nsContent.gone()
                        toolbar.visible()
                        ltLoading.setAnimation(R.raw.loading)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                    }
                    is DetailState.Result -> {
                        nsContent.visible()
                        toolbar.invisible()
                        ltLoading.gone()

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                            window.decorView.systemUiVisibility =
                                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                        } else {
                            window.setDecorFitsSystemWindows(false)
                        }

                        setupUI(it.data)
                    }
                    is DetailState.Error -> {
                        nsContent.gone()
                        toolbar.visible()
                        ltLoading.setAnimation(R.raw.error)
                        ltLoading.playAnimation()
                        ltLoading.visible()

                        if (it.error.message != null) {
                            it.error.printStackTrace()
                            Snackbar.make(root, it.error.message!!, Snackbar.LENGTH_INDEFINITE)
                                .setAction(
                                    getString(
                                        R.string.title_try_again
                                    )
                                ) {
                                    viewModel.getDetail(data.id)
                                }.show()
                        }
                    }
                }
            }
            viewModel.getDetail(data.id)
        }
    }

    private fun setupUI(data: DetailMovieObject){
        with(binding){
            ivBackground.load(BuildConfig.IMAGE_URL + data.movie.backdrop_path){
                crossfade(true)
                placeholder(R.drawable.placeholder_landscape)
            }
            ivImage.load(BuildConfig.IMAGE_URL + data.movie.poster_path){
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
                transformations(RoundedCornersTransformation(25f))
            }
            tvTitle.text = data.movie.title
            tvDate.dateFormat(data.movie.release_date!!, "yyyy-MM-dd", "dd MMMM yyyy")
            tvOverview.text = data.movie.overview
            tvUserReview.text = String.format(
                getString(R.string.placeholder_user_review),
                data.review.total_results
            )

            genre.submitList(data.movie.genres)
            if(data.cast.cast != null) cast.submitList(data.cast.cast)
            if(data.trailer.results != null) trailer.submitList(data.trailer.results)

        }
    }

    private fun setupToolbar(){
        with(binding){
            tvToolbar.text = data.title
            tvEmpty.text = String.format(
                getString(R.string.placeholder_empty_review),
                data.title
            )
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                    }
                    Configuration.UI_MODE_NIGHT_NO  -> {
                        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                    }
                }
            } else {
                window.setDecorFitsSystemWindows(true)
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                nsContent.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    if(scrollY > 250){
                        toolbar.visible()
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                                Configuration.UI_MODE_NIGHT_YES -> {
                                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                                }
                                Configuration.UI_MODE_NIGHT_NO  -> {
                                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                                }
                            }
                        } else {
                            window.setDecorFitsSystemWindows(true)
                        }
                    }else{
                        toolbar.invisible()
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                                Configuration.UI_MODE_NIGHT_YES -> {
                                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                                }
                                Configuration.UI_MODE_NIGHT_NO  -> {
                                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                                }
                            }
                        } else {
                            window.setDecorFitsSystemWindows(false)
                        }
                    }
                }
            }
        }
    }

    private val genre : GenreAdapter by lazy {
        GenreAdapter()
    }

    private val cast : CastAdapter by lazy {
        CastAdapter()
    }

    private val trailer : TrailerAdapter by lazy {
        TrailerAdapter()
    }

    private val viewModel : DetailViewModel by viewModels()
    private val binding : ActivityDetailBinding by viewBinding()
    private val data : DataDiscover by intent("data")
}