package com.example.kompaspolityczny.screens.result

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.database.TestResultDatabaseDao


class ResultViewModel(
    testResultKey: Long,
    dataSource: TestResultDatabaseDao
) : ViewModel() {

    private val result = MediatorLiveData<TestResult>()
    val database = dataSource

    fun getResult() = result

    init {
        result.addSource(database.getNightWithId(testResultKey), result::setValue)
    }
}