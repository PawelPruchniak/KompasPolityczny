package com.example.kompaspolityczny.screens.about


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kompaspolityczny.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {


    private val viewModel: AboutFragmentViewModel by lazy {
        ViewModelProvider(this).get(AboutFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAboutBinding.inflate(inflater)

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

}