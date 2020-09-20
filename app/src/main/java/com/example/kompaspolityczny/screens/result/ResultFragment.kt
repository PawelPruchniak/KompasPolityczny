@file:Suppress("DEPRECATION")

package com.example.kompaspolityczny.screens.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Setting binding
        val binding: ResultFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.result_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao

        val arguments = ResultFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory =
            ResultViewModelFactory(arguments.testResultKey, dataSource, application)
        val resultViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModel = resultViewModel

        binding.lifecycleOwner = this

        resultViewModel.eventMoveToTestHistory.observe(viewLifecycleOwner, { isTrue ->
            if (isTrue) {
                println("Jestem tutaj")
                this.findNavController().navigate(
                    ResultFragmentDirections.actionResultFragmentToHistoryFragment()
                )
                resultViewModel.onMoveToTestHistoryComplete()
            }
        })


        return binding.root
    }
}