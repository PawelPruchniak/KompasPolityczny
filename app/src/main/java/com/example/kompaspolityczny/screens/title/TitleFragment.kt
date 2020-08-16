package com.example.kompaspolityczny.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.kompaspolityczny.R
import com.example.kompaspolityczny.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        (activity as AppCompatActivity).supportActionBar?.displayOptions = 0

        binding.startTestButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToTestFragment())
        }

        binding.historyButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToHistoryFragment())
        }

        return binding.root
    }

}