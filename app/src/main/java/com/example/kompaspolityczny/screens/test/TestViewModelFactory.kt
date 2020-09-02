package com.example.kompaspolityczny.screens.test

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import com.example.kompaspolityczny.screens.history.HistoryFragmentViewModel
import com.example.kompaspolityczny.screens.result.ResultViewModel

class TestViewModelFactory(
    private val dataSource: TestResultDatabaseDao,
    private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            return TestViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}