package com.example.kompaspolityczny.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.databinding.ListItemTestResultBinding

class TestResultAdapter(private val clickListener: TestResultListener) :
    ListAdapter<TestResult, TestResultAdapter.ViewHolder>(TestResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemTestResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TestResult, clickListener: TestResultListener) {
            binding.result = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemTestResultBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


}


class TestResultDiffCallback : DiffUtil.ItemCallback<TestResult>() {
    override fun areItemsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
        return oldItem.resultId == newItem.resultId
    }

    override fun areContentsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
        return oldItem == newItem
    }
}

class TestResultListener(val clickListener: (resultId: Long) -> Unit) {
    fun onClick(result: TestResult) = clickListener(result.resultId)
}