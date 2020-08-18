package com.example.kompaspolityczny.screens.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(categoriesResult: FloatArray) : ViewModel() {

    private val _results = MutableLiveData<FloatArray>()
    val results: LiveData<FloatArray>
        get() = _results

    init {
        _results.value = categoriesResult
    }
}