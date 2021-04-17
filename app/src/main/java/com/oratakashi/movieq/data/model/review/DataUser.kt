package com.oratakashi.movieq.data.model.review

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("username") val username : String?,
    @SerializedName("avatar_path") val avatar_path : String?,
)
