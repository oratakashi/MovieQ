package com.oratakashi.movieq.data.model.trailer

import com.google.gson.annotations.SerializedName

data class DataTrailer(
    @SerializedName("id") val id : String?,
    @SerializedName("key") val key : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("site") val site : String?
)
