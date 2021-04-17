package com.oratakashi.movieq.ui.detail

import com.oratakashi.movieq.data.`object`.DetailMovieObject

sealed class DetailState {
    object Loading : DetailState()
    data class Result(val data : DetailMovieObject) : DetailState()
    data class Error(val error : Throwable) : DetailState()
}
