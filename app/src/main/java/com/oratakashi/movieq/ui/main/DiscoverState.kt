package com.oratakashi.movieq.ui.main

import com.oratakashi.movieq.data.model.discover.ResponseDiscover

sealed class DiscoverState {
    object Loading : DiscoverState()
    data class Result(val data : ResponseDiscover) : DiscoverState()
    data class Error(val error : Throwable) : DiscoverState()
}
