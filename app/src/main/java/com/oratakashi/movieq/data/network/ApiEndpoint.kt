package com.oratakashi.movieq.data.network

import com.oratakashi.movieq.data.model.discover.ResponseDiscover
import com.oratakashi.movieq.data.model.genre.ResponseGenre
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("genre/movie/list")
    fun getGenre() : Single<ResponseGenre>

    @GET("discover/movie")
    fun getDiscover(
        @Query("with_genres") genre : Int,
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String = "popularity.desc"
    ) : Single<ResponseDiscover>
}