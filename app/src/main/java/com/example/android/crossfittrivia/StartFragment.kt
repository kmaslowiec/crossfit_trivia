package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Listener for the OptionsMenu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)
    }

    /**
     * Listener for items in the OptionsMenu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
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