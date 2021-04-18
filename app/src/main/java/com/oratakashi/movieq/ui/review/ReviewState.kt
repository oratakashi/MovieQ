package com.oratakashi.movieq.ui.review

import com.oratakashi.movieq.data.model.review.ResponseReview

sealed class ReviewState {
    object Loading : ReviewState()
    data class Result(val data : ResponseReview) : ReviewState()
    data class Error(val error : Throwable) : ReviewState()
}
