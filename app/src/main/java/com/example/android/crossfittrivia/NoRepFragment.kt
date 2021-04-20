package com.example.android.crossfittrivia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentNoRepBinding
import com.example.android.crossfittrivia.databinding.FragmentStartBinding
import kotlin.properties.Delegates

class NoRepFragment : Fragment() {

    lateinit var binding: FragmentNoRepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Set fragment's title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.no_rep_title)

        //Set ViewBinding for the Fragment
        binding = FragmentNoRepBinding.inflate(inflater)

        binding.backToGameButton.setOnClickListener {
            view?.findNavController()?.navigate(NoRepFragmentDirections
                .actionNoRepFragmentToEmomFragment())
        }

        return binding.root
    }
}