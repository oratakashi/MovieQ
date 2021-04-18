package com.oratakashi.movieq.data.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.ui.detail.DetailState
import com.oratakashi.movieq.ui.genre.GenreState
import com.oratakashi.movieq.ui.main.DiscoverState
import com.oratakashi.movieq.ui.review.ReviewState

interface Repository {
    fun getGenre(state: MutableLiveData<GenreState>)
    fun getDiscover(
        genre : Int,
        callback: MutableLiveData<DiscoverState>,
        data: MutableLiveData<PagedList<DataDiscover>>,
        lifecycleOwner: LifecycleOwner
    )
    fun getReview(
        id : String,
        callback: MutableLiveData<ReviewState>,
        data: MutableLiveData<PagedList<DataReview>>,
        lifecycleOwner: LifecycleOwner
    )
    fun getDetail(id: Int, state: MutableLiveData<DetailState>)
}