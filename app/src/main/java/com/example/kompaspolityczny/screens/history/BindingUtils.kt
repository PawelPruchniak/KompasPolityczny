package com.example.kompaspolityczny.screens.history

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
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

@BindingAdapter("testResultDotImageHistoryFragment")
fun ImageView.setTestResultDotImageHistoryFragment(item: TestResult) {
    item.let {
        setImageResource(R.drawable.ic_dot)
        this.x = (item.osX * 10) - 100
        this.y = (item.osY * 10) - 100
    }
}

@BindingAdapter("testResultDotImageResultFragment")
fun ImageView.setTestResultDotImageResultFragment(item: MediatorLiveData<TestResult>) {
    item.let {
        if (item.value?.osX != null) {
            x = (item.value?.osX!! * 40) + 400
        }
        if (item.value?.osY != null) {
            y = (item.value?.osY!! * 40) + 400
        }
    }
}
