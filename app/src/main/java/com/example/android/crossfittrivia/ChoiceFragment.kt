package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChoiceBinding.inflate(inflater)

        emomButton(binding)

        // Inflate the layout for this fragment
        return binding.root
    }

    /*
* Init to_emom_button
 */
    private fun emomButton(binding: FragmentChoiceBinding) {
        binding.toEmomButton.setOnClickListener { view ->

            view.findNavController()
                .navigate(ChoiceFragmentDirections.actionChoiceFragmentToEmomFragment())
        }
    }
}