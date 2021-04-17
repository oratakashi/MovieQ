package com.oratakashi.movieq.data.model.genre

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
) : Parcelable
