package com.example.kompaspolityczny.screens.title

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleFragmentViewModel : ViewModel() {

    private val _navigateToHistoryFragment = MutableLiveData<Boolean>()
    val navigateToHistoryFragment: LiveData<Boolean>
        get() = _navigateToHistoryFragment


    private val _navigateToTestFragment = MutableLiveData<Boolean>()
    val navigateToTestFragment: LiveData<Boolean>
        get() = _navigateToTestFragment

    fun onToHistoryButtonClicked() {
        _navigateToHistoryFragment.value = true
    }

    fun onNavigatedToHistoryFragmentDone() {
        _navigateToHistoryFragment.value = false
    }

    fun onToTestButtonClicked() {
        _navigateToTestFragment.value = true
    }

    fun onNavigatedToTestFragmentDone() {
        _navigateToTestFragment.value = false
    }
}