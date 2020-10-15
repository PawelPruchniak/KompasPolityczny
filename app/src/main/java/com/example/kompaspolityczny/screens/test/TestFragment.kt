package com.example.kompaspolityczny.screens.test

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
import com.example.kompaspolityczny.databinding.TestFragmentBinding

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Setting binding
        val binding: TestFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.test_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao

        val viewModelFactory = TestViewModelFactory(dataSource, application)
        val testViewModel = ViewModelProvider(this, viewModelFactory).get(
            TestViewModel::class.java
        )

        binding.testViewModel = testViewModel
        binding.lifecycleOwner = this

        testViewModel.eventTestFinish.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished) {
                val results: FloatArray = testViewModel.categoryResultList
                testViewModel.addResultsToDatabase(results)
                testViewModel.onTestFinishComplete()
            }
        })

        testViewModel.eventMoveToTestResult.observe(viewLifecycleOwner, { isTrue ->
            if (isTrue) {
                this.findNavController().navigate(
                    TestFragmentDirections.actionTestFragmentToResultFragment(testViewModel.lastResult)
                )
                testViewModel.onMoveToTestResultComplete()
            }
        })

        return binding.root
    }
}