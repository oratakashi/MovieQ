package com.oratakashi.movieq.data.`object`

import com.oratakashi.movieq.data.model.cast.ResponseCast
import com.oratakashi.movieq.data.model.movie.ResponseMovie
import com.oratakashi.movieq.data.model.review.ResponseReview
import com.oratakashi.movieq.data.model.trailer.ResponseTrailer

data class DetailMovieObject(
    val movie: ResponseMovie,
    val trailer: ResponseTrailer,
    val review: ResponseReview,
    val cast: ResponseCast
)
