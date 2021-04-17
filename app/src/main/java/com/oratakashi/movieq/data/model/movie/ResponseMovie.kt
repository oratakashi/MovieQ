package com.oratakashi.movieq.data.model.movie

import com.google.gson.annotations.SerializedName
import com.oratakashi.movieq.data.model.genre.DataGenre

data class ResponseMovie(
    @SerializedName("id") val id : String?,
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("overview") val overview : String?,
    @SerializedName("genres") val genres : List<DataGenre>,
)
