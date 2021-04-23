package com.example.android.crossfittrivia

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.data.GameData
import com.example.android.crossfittrivia.databinding.FragmentNoRepBinding

class NoRepFragment : Fragment() {

    private lateinit var binding: FragmentNoRepBinding
    private val model: GameViewModel by activityViewModels()
    private var answeredQuestions = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Set fragment's title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.no_rep_title)

        //Set ViewBinding for the Fragment
        binding = FragmentNoRepBinding.inflate(inflater)

        setObserver()

        returnButton()

        return binding.root
    }

    //Get questionLimit from SharedPreferences
    private fun getQuestionLimit(): Int {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPref?.getInt("questions", 0)!!
    }

    // Set Observer
    private fun setObserver() {
        val gameObserver = Observer<GameData> { data ->
            answeredQuestions = data.answeredQuestions
        }

        //Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)
    }

    //Returns to EMOM or Result fragment
    private fun returnButton() {
        binding.backToGameButton.setOnClickListener {
            if (answeredQuestions < getQuestionLimit()) {
                view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToEmomFragment())
            } else {
                view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToResultsFragment())
            }
        }
    }
}