package com.example.kompaspolityczny.screens.history

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kompaspolityczny.database.TestResult

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultID")
fun TextView.setTestResultID(item: TestResult){
    item?.let {
        text = "ID: ${item.resultId}"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultGospodarkaLeft")
fun TextView.setTestResultGospodarkaLeft(item: TestResult){
    item?.let {
        text = "Data wykonania testu: ${item.testDate}"
    }
}