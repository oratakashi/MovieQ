package com.oratakashi.movieq.data.model.genre

import com.google.gson.annotations.SerializedName

data class ResponseGenre(
    @field:SerializedName("genres") val genres : List<DataGenre>,
)
