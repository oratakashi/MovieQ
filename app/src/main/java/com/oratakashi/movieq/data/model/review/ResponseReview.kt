package com.oratakashi.movieq.data.model.review

import com.google.gson.annotations.SerializedName

data class ResponseReview (
    @SerializedName("results") val results : List<DataReview>?,
    @SerializedName("total_results") val total_results : String?,
)