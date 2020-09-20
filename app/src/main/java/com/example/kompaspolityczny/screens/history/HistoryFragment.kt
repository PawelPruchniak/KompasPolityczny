package com.example.kompaspolityczny.screens.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao


        val viewModelFactory = HistoryFragmentViewModelFactory(dataSource, application)
        val historyViewModel =
            ViewModelProvider(this, viewModelFactory).get(HistoryFragmentViewModel::class.java)
        binding.historyViewModel = historyViewModel

        val adapter = TestResultAdapter(TestResultListener { resultId ->
            historyViewModel.onTestResultClicked(resultId)
        })
        binding.resultList.adapter = adapter
        historyViewModel.results.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        historyViewModel.navigateToResultFragment.observe(viewLifecycleOwner, { resultId ->
            resultId?.let {
                this.findNavController().navigate(
                    HistoryFragmentDirections.actionHistoryFragmentToResultFragment(resultId)
                )
                historyViewModel.onTestResultNavigated()
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}
