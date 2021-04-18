package com.oratakashi.movieq.ui.review

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.oratakashi.movieq.data.model.review.DataReview
import com.oratakashi.movieq.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val state : MutableLiveData<ReviewState> by lazy {
        MutableLiveData()
    }

    val data : MutableLiveData<PagedList<DataReview>> by lazy {
        MutableLiveData()
    }

    fun getReview(id : String, lifecycleOwner: LifecycleOwner) {
        repository.getReview(id, state, data, lifecycleOwner)
    }
}