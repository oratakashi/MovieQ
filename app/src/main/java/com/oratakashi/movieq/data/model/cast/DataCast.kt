package com.oratakashi.movieq.data.model.cast

import com.google.gson.annotations.SerializedName

data class DataCast(
    @SerializedName("name") val name : String?,
    @SerializedName("character") val character : String?,
    @SerializedName("profile_path") val profile_path : String?
)
