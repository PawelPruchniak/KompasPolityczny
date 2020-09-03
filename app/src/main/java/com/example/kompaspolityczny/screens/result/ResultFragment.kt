package com.example.kompaspolityczny.screens.result

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.database.TestResultDatabase
import com.example.kompaspolityczny.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

            // Setting binding
            val binding: ResultFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.result_fragment,
                container,
                false
            )

            val application = requireNotNull(this.activity).application

            val dataSource = TestResultDatabase.getInstance(application).testResultDatabaseDao

            val resultFragmentArgs by navArgs<ResultFragmentArgs>()

            viewModelFactory = ResultViewModelFactory(resultFragmentArgs.testResultKey, dataSource)

            viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResultViewModel::class.java)

            binding.resultViewModel = viewModel

            binding.setLifecycleOwner(this)

            return binding.root
        }
    }