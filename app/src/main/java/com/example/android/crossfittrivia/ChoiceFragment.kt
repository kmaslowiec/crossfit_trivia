package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Change fragment title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.choice_title)

        binding = FragmentChoiceBinding.inflate(inflater)

        emomButton()

        // Inflate the layout for this fragment
        return binding.root
    }


    // Init toEmomButton
    private fun emomButton() {
        binding.toEmomButton.setOnClickListener { view ->

            view.findNavController()
                .navigate(ChoiceFragmentDirections.actionChoiceFragmentToEmomFragment())
        }
    }
}