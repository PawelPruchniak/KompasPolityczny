@file:Suppress("DEPRECATION")

package com.example.kompaspolityczny.screens.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

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

        viewModelFactory = ResultViewModelFactory(arguments.testResultKey, dataSource)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}