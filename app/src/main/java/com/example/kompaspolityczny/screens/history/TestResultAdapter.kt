package com.example.kompaspolityczny.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.databinding.ListItemTestResultBinding

class TestResultAdapter: ListAdapter<TestResult, TestResultAdapter.ViewHolder>(TestResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TestResultAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemTestResultBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: TestResult) {
            binding.result = item
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




class TestResultDiffCallback: DiffUtil.ItemCallback<TestResult>(){
    override fun areItemsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
        return oldItem.resultId == newItem.resultId
    }

    override fun areContentsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
        return oldItem == newItem
    }
}