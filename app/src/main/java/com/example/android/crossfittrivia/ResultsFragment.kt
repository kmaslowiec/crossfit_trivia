package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android.crossfittrivia.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.results_title)
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        return binding.root
    }
}