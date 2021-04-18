package com.oratakashi.movieq.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.data.source.ReviewSource
import com.oratakashi.movieq.ui.review.ReviewState
import javax.inject.Inject

class ReviewFactory @Inject constructor(
    private val source: ReviewSource
) : DataSource.Factory<Int, DataReview>() {

    lateinit var id : String
    lateinit var liveData: MutableLiveData<ReviewState>

    override fun create(): DataSource<Int, DataReview> {
        return source.also {
            it.id = id
            it.liveData = liveData
        }
    }
}