package com.example.android.crossfittrivia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.android.crossfittrivia.databinding.FragmentStartBinding
import kotlin.properties.Delegates

class NoRepFragment : Fragment() {

    lateinit var binding: FragmentNoRepBinding
    private val model: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var answeredQuestions = 0

        //Set fragment's title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.no_rep_title)
        
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val numQuestions = sharedPref?.getInt("questions", 0)

        //Set ViewBinding for the Fragment
        binding = FragmentNoRepBinding.inflate(inflater)

        // Set Observer
        val gameObserver = Observer<GameData> { data ->
            answeredQuestions = data.answeredQuestions
        }

        //Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)

        binding.backToGameButton.setOnClickListener {
            if (answeredQuestions<numQuestions!!){
                view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToEmomFragment())
            }else{
                view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToResultsFragment())
            }
        }

        return binding.root
    }
}