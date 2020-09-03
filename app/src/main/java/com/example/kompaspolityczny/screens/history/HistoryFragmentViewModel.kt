package com.example.kompaspolityczny.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.kompaspolityczny.database.TestResultDatabaseDao
import kotlinx.coroutines.*

class HistoryFragmentViewModel(
    val database: TestResultDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val results = database.getAllResults()

    val clearButtonVisible = Transformations.map(results) {
        it?.isNotEmpty()
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onClear() {
        uiScope.launch {
            // Clear the database table.
            clear()
        }
        // Show a snackbar message, because it's friendly.
        //_showSnackbarEvent.value = true
    }

}