package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_start, container, false)
        val startButton : Button = rootView.findViewById(R.id.start_button)

        startButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }

        // Inflate the layout for this fragment
        return rootView
    }
}