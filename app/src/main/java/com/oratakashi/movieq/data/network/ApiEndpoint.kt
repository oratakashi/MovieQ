package com.oratakashi.movieq.data.network

import com.oratakashi.movieq.data.model.cast.ResponseCast
import com.oratakashi.movieq.data.model.discover.ResponseDiscover
import com.oratakashi.movieq.data.model.genre.ResponseGenre
import com.oratakashi.movieq.data.model.movie.ResponseMovie
import com.oratakashi.movieq.data.model.review.ResponseReview
import com.oratakashi.movieq.data.model.trailer.ResponseTrailer
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("genre/movie/list")
    fun getGenre(): Single<ResponseGenre>

    @GET("discover/movie")
    fun getDiscover(
        @Query("with_genres") genre: Int,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Single<ResponseDiscover>

    @GET("movie/{id}/reviews")
    fun getReviews(
        @Path("id") id : String,
        @Query("page") page : Int
    ) : Single<ResponseReview>

    @GET("movie/{id}")
    fun getObsMovie(
        @Path("id") id: Int
    ): Observable<ResponseMovie>

    @GET("movie/{id}/videos")
    fun getObsTrailer(
        @Path("id") id: Int
    ): Observable<ResponseTrailer>

    @GET("movie/{id}/reviews")
    fun getObsReviews(
        @Path("id") id: Int
    ): Observable<ResponseReview>

    @GET("movie/{id}/credits")
    fun getObsCast(
        @Path("id") id: Int
    ): Observable<ResponseCast>
}