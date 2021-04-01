package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * Setups data binding for the Fragment
         */
        val binding: FragmentStartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)

        /**
         * findViewById without Data Binding
         */
        /*val rootView: View = inflater.inflate(R.layout.fragment_start, container, false)
        val startButton : Button = rootView.findViewById(R.id.start_button)*/

        startButton(binding)

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun startButton(binding: FragmentStartBinding) {
        //binding.startButton.setOnClickListener(Navigation
        // .createNavigateOnClickListener(R.id.action_startFragment_to_gameFragment))
        binding.startButton.setOnClickListener { view ->
            /**
             * Using Safe Args it passes the data as a parameter
             */
            view.findNavController()
                .navigate(StartFragmentDirections.actionStartFragmentToGameFragment("Hermes"))
        }
    }
}