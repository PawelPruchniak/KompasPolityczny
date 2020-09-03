package com.example.kompaspolityczny.screens.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Instant
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


class ResultViewModel(
    private val testResultKey: Long,
     dataSource: TestResultDatabaseDao
) : ViewModel() {

    private val result = MediatorLiveData<TestResult>()
    val database = dataSource

    fun getResult() = result

    init {
            println(testResultKey)
            result.addSource(database.getNightWithId(testResultKey), result::setValue)
    }
}