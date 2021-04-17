package com.oratakashi.movieq.data.model.trailer

import com.google.gson.annotations.SerializedName

data class ResponseTrailer(
    @SerializedName("results") val results : List<DataTrailer>?
)
