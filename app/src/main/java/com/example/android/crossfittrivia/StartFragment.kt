package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Setup viewBinding for the Fragment
        binding = FragmentStartBinding.inflate(inflater)

        startButton()

        // Inflate the layout for this fragment
        return binding.root
    }

    // Init toChoiceButton
    private fun startButton() {
        binding.toChoiceButton.setOnClickListener { view ->
            /**
             * Using Safe Args it passes the data as a parameter
             */
            view.findNavController()
                .navigate(StartFragmentDirections.actionStartFragmentToChoiceFragment())
        }
    }
}