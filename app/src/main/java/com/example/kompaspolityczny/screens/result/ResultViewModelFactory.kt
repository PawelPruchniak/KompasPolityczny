package com.example.kompaspolityczny.screens.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ResultViewModelFactory(private val categoriesResult: FloatArray) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(categoriesResult) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}