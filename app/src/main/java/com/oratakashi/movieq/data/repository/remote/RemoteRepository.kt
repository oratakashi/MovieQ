package com.oratakashi.movieq.data.repository.remote

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oratakashi.movieq.data.`object`.DetailMovieObject
import com.oratakashi.movieq.data.factory.Factory
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.data.repository.Repository
import com.oratakashi.movieq.ui.detail.DetailState
import com.oratakashi.movieq.ui.genre.GenreState
import com.oratakashi.movieq.ui.main.DiscoverState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val disposable: CompositeDisposable,
    private val endpoint: ApiEndpoint,
    private val factory: Factory
) : Repository {
    override fun getGenre(state: MutableLiveData<GenreState>) {
        endpoint.getGenre()
            .map<GenreState>(GenreState::Result)
            .onErrorReturn(GenreState::Error)
            .toFlowable()
            .startWith(GenreState.Loading)
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getDetail(id: Int, state: MutableLiveData<DetailState>) {
        Observable.zip(
            endpoint.getObsMovie(id).subscribeOn(Schedulers.io()),
            endpoint.getObsTrailer(id).subscribeOn(Schedulers.io()),
            endpoint.getObsReviews(id).subscribeOn(Schedulers.io()),
            endpoint.getObsCast(id).subscribeOn(Schedulers.io()),
            { movie, trailer, review, cast ->
                DetailMovieObject(movie, trailer, review, cast)
            }
        )
            .map<DetailState>(DetailState::Result)
            .onErrorReturn(DetailState::Error)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(DetailState.Loading)
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getDiscover(
        genre: Int,
        callback: MutableLiveData<DiscoverState>,
        data: MutableLiveData<PagedList<DataDiscover>>,
        lifecycleOwner: LifecycleOwner
    ) {
        LivePagedListBuilder(
            factory.discover.also { it.liveData = callback;it.genre = genre },
            10
        ).build().observe(lifecycleOwner, data::postValue)
    }
}