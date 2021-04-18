package com.oratakashi.movieq.data.model.trailer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTrailer(
    @SerializedName("id") val id : String?,
    @SerializedName("key") val key : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("site") val site : String?
) : Parcelable
