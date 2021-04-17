package com.oratakashi.movieq.data.model.discover

import com.google.gson.annotations.SerializedName

data class ResponseDiscover(
    @SerializedName("results") val data : List<DataDiscover>?,
    @SerializedName("page") val page : Int?,
    @SerializedName("total_pages") val total_pages : Int?
)
