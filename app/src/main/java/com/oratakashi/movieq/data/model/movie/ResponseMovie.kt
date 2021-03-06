package com.oratakashi.movieq.data.model.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.oratakashi.movieq.data.model.genre.DataGenre
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseMovie(
    @SerializedName("id") val id : String?,
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("overview") val overview : String?,
    @SerializedName("release_date") val release_date : String?,
    @SerializedName("vote_average") val vote_average : Float?,
    @SerializedName("genres") val genres : List<DataGenre>,
) : Parcelable
