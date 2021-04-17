package com.oratakashi.movieq.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.ui.main.DiscoverState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DiscoverSource @Inject constructor(
    private val endpoint: ApiEndpoint,
    private val disposable: CompositeDisposable
) : PageKeyedDataSource<Int, DataDiscover>() {

    lateinit var liveData : MutableLiveData<DiscoverState>
    var genre : Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataDiscover>
    ) {
        endpoint.getDiscover(genre, 1)
            .map<DiscoverState>{
                callback.onResult(it.data!!, 1, 2)
                DiscoverState.Result(it)
            }
            .onErrorReturn(DiscoverState::Error)
            .toFlowable()
            .startWith(DiscoverState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataDiscover>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataDiscover>) {
        endpoint.getDiscover(genre, params.key)
            .map<DiscoverState>{
                callback.onResult(it.data!!, params.key + 1)
                DiscoverState.Result(it)
            }
            .onErrorReturn(DiscoverState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }
}