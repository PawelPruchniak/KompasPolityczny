package com.example.kompaspolityczny.screens.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao

        val viewModelFactory = HistoryFragmentViewModelFactory(dataSource, application)

        val historyViewModel = ViewModelProvider(this, viewModelFactory).get(HistoryFragmentViewModel::class.java)

        binding.historyViewModel = historyViewModel

        val adapter = TestResultAdapter()
        binding.resultList.adapter = adapter
        historyViewModel.results.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}