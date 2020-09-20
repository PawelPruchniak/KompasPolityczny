package com.example.kompaspolityczny.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HistoryFragmentViewModel(
    val database: TestResultDatabaseDao,
    application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val results = database.getAllResults()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}