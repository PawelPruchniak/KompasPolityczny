package com.example.kompaspolityczny.screens.test

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResult
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.TestFragmentBinding
import com.example.kompaspolityczny.screens.history.HistoryFragmentViewModel
import com.example.kompaspolityczny.screens.history.HistoryFragmentViewModelFactory

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Setting binding
        val binding: TestFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.test_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao
        val viewModelFactory = TestViewModelFactory(dataSource, application)
        val testViewModel = ViewModelProvider(this, viewModelFactory).get(
            TestViewModel::class.java)

        binding.testViewModel = testViewModel
        binding.setLifecycleOwner(this)

        testViewModel.eventTestFinish.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                val results: FloatArray = testViewModel.categoryResultList
                testViewModel.addResultsToDatabase(results)
                val action = TestFragmentDirections.actionTestFragmentToResultFragment(results)
                NavHostFragment.findNavController(this).navigate(action)
                testViewModel.onTestFinishComplete()
            }
        })

        return binding.root
    }
}