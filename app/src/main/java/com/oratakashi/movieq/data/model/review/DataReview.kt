package com.oratakashi.movieq.data.model.review

import com.google.gson.annotations.SerializedName

data class DataReview(
    @SerializedName("author_details") val author_details : DataUser?,
    @SerializedName("content") val content : String?,
    @SerializedName("updated_at") val updated_at : String?,
    @SerializedName("id") val id : String?,
)
