package com.example.android.crossfittrivia

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android.crossfittrivia.databinding.FragmentChoiceBinding
import com.example.android.crossfittrivia.utils.GameMode

class ChoiceFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Change fragment title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.choice_title)

        binding = FragmentChoiceBinding.inflate(inflater)

        initEmomButton()
        initAmrapButton()
        initChipperButton()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initEmomButton() {
        binding.toEmomButton.setOnClickListener { view ->

            view.findNavController()
                .navigate(ChoiceFragmentDirections.actionChoiceFragmentToEmomFragment(GameMode.EMOM))
        }
    }

    private fun initAmrapButton() {
        binding.toAmrapButton.setOnClickListener { view ->
            view.findNavController().navigate(
                ChoiceFragmentDirections
                    .actionChoiceFragmentToEmomFragment(GameMode.AMRAP)
            )
        }
    }

    private fun initChipperButton() {
        binding.toChipperButton.setOnClickListener { view ->
            view.findNavController().navigate(
                ChoiceFragmentDirections
                    .actionChoiceFragmentToEmomFragment(GameMode.CHIPPER)
            )
        }
    }
}