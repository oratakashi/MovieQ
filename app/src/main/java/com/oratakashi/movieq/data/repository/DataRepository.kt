package com.oratakashi.movieq.data.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.repository.remote.RemoteRepository
import com.oratakashi.movieq.ui.detail.DetailState
import com.oratakashi.movieq.ui.genre.GenreState
import com.oratakashi.movieq.ui.main.DiscoverState
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : Repository {
    override fun getGenre(state: MutableLiveData<GenreState>) {
        remoteRepository.getGenre(state)
    }

    override fun getDetail(id: Int, state: MutableLiveData<DetailState>) {
        remoteRepository.getDetail(id, state)
    }

    override fun getDiscover(
        genre : Int,
        callback: MutableLiveData<DiscoverState>,
        data: MutableLiveData<PagedList<DataDiscover>>,
        lifecycleOwner: LifecycleOwner
    ) {
        remoteRepository.getDiscover(genre, callback, data, lifecycleOwner)
    }
}