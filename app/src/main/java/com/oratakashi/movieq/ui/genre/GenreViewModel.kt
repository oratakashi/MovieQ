package com.oratakashi.movieq.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.movieq.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val state : MutableLiveData<GenreState> by lazy {
        MutableLiveData()
    }

    fun getGenre(){
        repository.getGenre(state)
    }
}