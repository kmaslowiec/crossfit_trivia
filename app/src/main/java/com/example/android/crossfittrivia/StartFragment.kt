package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * Setup view binding for the Fragment
         */
        val binding: FragmentStartBinding = FragmentStartBinding.inflate(inflater)

        //Init to_choice_button
        startButton(binding)

        // Inflate the layout for this fragment
        return binding.root
    }

    /*
    * Init to_choice_button
     */
    private fun startButton(binding: FragmentStartBinding) {
        binding.startButton.setOnClickListener { view ->
            /**
             * Using Safe Args it passes the data as a parameter
             */
            view.findNavController()
                .navigate(StartFragmentDirections.actionStartFragmentToChoiceFragment("Hermes"))
        }
    }
}