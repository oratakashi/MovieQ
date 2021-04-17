package com.oratakashi.movieq.ui.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.oratakashi.movieq.data.model.discover.DataDiscover
import com.oratakashi.movieq.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val state : MutableLiveData<DiscoverState> by lazy {
        MutableLiveData()
    }

    val data : MutableLiveData<PagedList<DataDiscover>> by lazy {
        MutableLiveData()
    }

    fun getDiscover(id : Int, lifecycleOwner: LifecycleOwner){
        repository.getDiscover(id, state, data, lifecycleOwner)
    }
}