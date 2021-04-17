package com.oratakashi.movieq.data.factory

import javax.inject.Inject

data class Factory @Inject constructor(
    val discover : DiscoverFactory
)
