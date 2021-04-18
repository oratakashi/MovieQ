package com.oratakashi.movieq.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.ui.review.ReviewState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ReviewSource @Inject constructor(
    private val endpoint: ApiEndpoint,
    private val disposable: CompositeDisposable
) : PageKeyedDataSource<Int, DataReview>() {

    lateinit var id : String
    lateinit var liveData: MutableLiveData<ReviewState>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataReview>
    ) {
        endpoint.getReviews(id, 1)
            .map<ReviewState>{
                callback.onResult(it.results!!, 1, 2)
                ReviewState.Result(it)
            }
            .onErrorReturn(ReviewState::Error)
            .toFlowable()
            .startWith(ReviewState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataReview>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataReview>) {
        endpoint.getReviews(id, params.key)
            .map<ReviewState>{
                callback.onResult(it.results!!, params.key + 1)
                ReviewState.Result(it)
            }
            .onErrorReturn(ReviewState::Error)
            .toFlowable()
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }
}