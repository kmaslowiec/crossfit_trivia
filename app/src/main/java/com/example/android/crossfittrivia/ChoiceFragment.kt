package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.crossfittrivia.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentChoiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choice, container, false)

        /**
         * Gets data from StartFragment
         */
        val args = ChoiceFragmentArgs.fromBundle(requireArguments())

        Toast.makeText(activity, args.helloText, Toast.LENGTH_LONG).show()

        // Inflate the layout for this fragment
        return binding.root
    }
}