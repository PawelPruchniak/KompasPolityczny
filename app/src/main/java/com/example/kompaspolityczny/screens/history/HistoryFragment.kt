package com.example.kompaspolityczny.screens.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.kompaspolityczny.R

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Setting ActionBar title
        (activity as AppCompatActivity).supportActionBar?.title = "History"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)


    }
}