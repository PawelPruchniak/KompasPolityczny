package com.example.kompaspolityczny.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.kompaspolityczny.database.TestResultDatabaseDao

class HistoryFragmentViewModel(
    val database: TestResultDatabaseDao,
    application: Application): AndroidViewModel(application) {

    val results = database.getAllResults()
}