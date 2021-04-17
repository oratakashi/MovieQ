package com.oratakashi.movieq.ui.genre

import com.oratakashi.movieq.data.model.genre.ResponseGenre

sealed class GenreState {
    object Loading : GenreState()
    data class Result(val data : ResponseGenre) : GenreState()
    data class Error(val error : Throwable) : GenreState()
}
