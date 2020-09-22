package com.example.kompaspolityczny.screens.history

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResult

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultID")
fun TextView.setTestResultID(item: TestResult) {
    item.let {
        text = "Test nr: ${item.resultId}"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("testResultDate")
fun TextView.setTestResultDate(item: TestResult) {
    var itemDate = item.testDate
    itemDate = itemDate.dropLast(4)
    itemDate = itemDate.replaceFirst("T", "  ")
    item.let {
        text = "Data: $itemDate"
    }
}

@BindingAdapter("testResultCompassImage")
fun ImageView.setTestResultCompassImage(item: TestResult) {
    item.let {
        setImageResource(R.drawable.ic_political_compass_without_text)
    }
}

@BindingAdapter("testResultDotImage")
fun ImageView.setTestResultDotImage(item: TestResult) {
    item.let {
        setImageResource(R.drawable.ic_dot)
        this.x = -100F
        this.y = -100F
    }
}
