package com.example.kompaspolityczny.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var viewModel: TitleFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTitleBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(TitleFragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToHistoryFragment.observe(viewLifecycleOwner,
            { shouldNavigate ->
                if (shouldNavigate == true) {
                    val navController = binding.root.findNavController()
                    navController.navigate(R.id.action_titleFragment_to_historyFragment)
                    viewModel.onNavigatedToHistoryFragmentDone()
                }
            })

        viewModel.navigateToTestFragment.observe(viewLifecycleOwner,
            { shouldNavigate ->
                if (shouldNavigate == true) {
                    val navController = binding.root.findNavController()
                    navController.navigate(R.id.action_titleFragment_to_testFragment)
                    viewModel.onNavigatedToTestFragmentDone()
                }
            })

        return binding.root
    }

}