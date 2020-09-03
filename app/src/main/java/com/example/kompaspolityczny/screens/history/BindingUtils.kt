package com.example.kompaspolityczny.screens.history

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResult

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultID")
fun TextView.setTestResultID(item: TestResult){
    item?.let {
        text = "Test nr: ${item.resultId}"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultDate")
fun TextView.setTestResultDate(item: TestResult){
    item?.let {
        text = "Data: ${item.testDate}"
    }
}

@BindingAdapter("testResultCompassImage")
fun ImageView.setTestResultCompassImage(item: TestResult){
    setImageResource(R.drawable.ic_launcher_background)

}

@BindingAdapter("gospodarkaLeft")
fun TextView.setGospodarkaLeft(item: TestResult){
    item?.let {
        text = "${item.gospodarkaLeft}"
    }
}