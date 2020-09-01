package com.example.kompaspolityczny.screens.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.*
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime

class ResultViewModel(
    categoriesResult: FloatArray,
    val database: TestResultDatabaseDao
) : ViewModel() {

    private val _results = MutableLiveData<FloatArray>()
    val results: LiveData<FloatArray>
        get() = _results

    private val viewModelJob = Job()
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _results.value = categoriesResult
        addResultToDatabase()
    }
    
    fun addResultToDatabase(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.clear()
                val testResult = TestResult()

                val currentDate: LocalDateTime = LocalDateTime.now(DateTimeZone.forID("Europe/Warsaw"))
                testResult.testDate = currentDate.toString()

                testResult.gospodarkaLeft = _results.value!![0].toInt()
                testResult.gospodarkaRight = _results.value!![1].toInt()

                testResult.spoleczenstwoLeft = _results.value!![2].toInt()
                testResult.spoleczenstwoRight = _results.value!![3].toInt()

                testResult.politykaWLeft = _results.value!![4].toInt()
                testResult.politykaWRight = _results.value!![5].toInt()

                testResult.politykaZLeft = _results.value!![6].toInt()
                testResult.politykaZRight = _results.value!![7].toInt()

                database.insert(testResult)
            }
        }
        Log.i("ResultViewModel", "Wyniki testu zostały dodane do bazy danych")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}