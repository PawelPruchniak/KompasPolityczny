package com.example.kompaspolityczny.screens.history

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kompaspolityczny.database.TestResult

@BindingAdapter("testResultID")
fun TextView.setTestResultID(item: TestResult){
    item?.let {
        text = item.resultId.toString()
    }
}

@BindingAdapter("testResultGospodarkaLeft")
fun TextView.setTestResultGospodarkaLeft(item: TestResult){
    item?.let {
        text =  item.gospodarkaLeft.toString()
    }
}