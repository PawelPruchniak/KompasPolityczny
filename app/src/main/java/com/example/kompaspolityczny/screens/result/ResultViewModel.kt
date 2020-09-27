package com.example.kompaspolityczny.screens.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.*


class ResultViewModel(
    private val testResultKey: Long,
    dataSource: TestResultDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val result = MediatorLiveData<TestResult>()

    val database = dataSource

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _eventMoveToTestHistory = MutableLiveData<Boolean>()
    val eventMoveToTestHistory: LiveData<Boolean>
        get() = _eventMoveToTestHistory

    fun getResult() = result

    init {
        result.addSource(database.getNightWithId(testResultKey), result::setValue)
    }

    fun onDelete() {
        uiScope.launch {
            delete()
        }
        // This should navigate to HistoryFragment -> but there are troubles
        _eventMoveToTestHistory.value = true
        Log.i("ResultViewModel", "TestResult was deleted!")
    }

    private suspend fun delete() {
        withContext(Dispatchers.IO) {
            database.deleteById(testResultKey)
        }
    }

    fun onMoveToTestHistoryComplete() {
        _eventMoveToTestHistory.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("ResultViewModel", "resultViewModel destroyed!")
    }
}