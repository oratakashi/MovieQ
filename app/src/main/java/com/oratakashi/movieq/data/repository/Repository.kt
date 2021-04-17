package com.oratakashi.movieq.data.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.ui.genre.GenreState
import com.oratakashi.movieq.ui.main.DiscoverState

interface Repository {
    fun getGenre(state: MutableLiveData<GenreState>)
    fun getDiscover(
        genre : Int,
        callback: MutableLiveData<DiscoverState>,
        data: MutableLiveData<PagedList<DataDiscover>>,
        lifecycleOwner: LifecycleOwner
    )
}