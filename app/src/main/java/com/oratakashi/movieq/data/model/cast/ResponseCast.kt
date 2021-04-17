package com.oratakashi.movieq.data.model.cast

import com.google.gson.annotations.SerializedName

data class ResponseCast(
    @SerializedName("cast") val cast : List<DataCast>?,
)
