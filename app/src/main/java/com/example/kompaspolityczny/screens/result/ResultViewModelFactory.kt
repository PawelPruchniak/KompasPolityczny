package com.example.kompaspolityczny.screens.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kompaspolityczny.database.TestResultDatabaseDao


class ResultViewModelFactory(
    private val testResultKey: Long,
    private val dataSource: TestResultDatabaseDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(testResultKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}