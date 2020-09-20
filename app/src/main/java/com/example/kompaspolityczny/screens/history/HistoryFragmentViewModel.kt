package com.example.kompaspolityczny.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HistoryFragmentViewModel(
    val database: TestResultDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val results = database.getAllResults()

    private val _navigateToResultFragment = MutableLiveData<Long>()
    val navigateToResultFragment: LiveData<Long>
        get() = _navigateToResultFragment


    fun onTestResultClicked(id: Long) {
        _navigateToResultFragment.value = id
    }

    fun onTestResultNavigated() {
        _navigateToResultFragment.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}