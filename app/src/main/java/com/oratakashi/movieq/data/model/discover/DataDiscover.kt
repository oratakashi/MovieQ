package com.oratakashi.movieq.data.model.discover

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDiscover (
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("id") val id : String,
    @SerializedName("overview") val overview : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("release_date") val release_date : String?
) : Parcelable