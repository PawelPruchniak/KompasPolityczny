package com.example.kompaspolityczny.screens.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kompaspolityczny.database.TestResultDatabaseDao


class ResultViewModelFactory(
    private val testResultKey: Long,
    private val dataSource: TestResultDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(testResultKey, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}