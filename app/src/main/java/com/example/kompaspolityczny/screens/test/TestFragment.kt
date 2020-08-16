package com.example.kompaspolityczny.screens.test

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.databinding.TestFragmentBinding

class TestFragment : Fragment() {

    private lateinit var viewModel: TestViewModel

    private lateinit var binding: TestFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Setting binding
        binding = DataBindingUtil.inflate(inflater, R.layout.test_fragment, container, false)

        // Setting viewModel
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)

        binding.testViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.eventTestFinish.observe(this, Observer { isFinished ->
            if (isFinished) {
                val currentQuestionsNumber = viewModel.questionNumber.value ?: 0
                val action = TestFragmentDirections.actionTestFragmentToResultFragment(currentQuestionsNumber)
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.onTestFinishComplete()
            }
        })

        return binding.root
    }
}