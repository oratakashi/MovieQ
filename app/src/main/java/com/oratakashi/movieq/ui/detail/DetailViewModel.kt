package com.oratakashi.movieq.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.movieq.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val state : MutableLiveData<DetailState> by lazy {
        MutableLiveData()
    }

    fun getDetail(id : Int) {
        repository.getDetail(id, state)
    }
}