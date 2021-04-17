package com.oratakashi.movieq.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.source.DiscoverSource
import com.oratakashi.movieq.ui.main.DiscoverState
import javax.inject.Inject

class DiscoverFactory @Inject constructor(
    private val source: DiscoverSource
) : DataSource.Factory<Int, DataDiscover>() {

    lateinit var liveData : MutableLiveData<DiscoverState>
    var genre : Int = 0

    override fun create(): DataSource<Int, DataDiscover> {
        return source.also {
            it.genre = genre
            it.liveData = liveData
        }
    }
}