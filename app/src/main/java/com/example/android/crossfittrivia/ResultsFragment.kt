package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.data.GameData
import com.example.android.crossfittrivia.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {
    private var answeredQuestions: Int = 0
    private var result: Int = 0
    private lateinit var binding: FragmentResultsBinding
    private val model: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.results_title)
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        setObserver()

        binding.scoreText.text = resources.getString(R.string.score_text, result)

        binding.againButton.setOnClickListener { view->
            model.currentGame.value = GameData(0,0)
            view.findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToEmomFragment())
        }


        return binding.root
    }


    // Set Observer
    private fun setObserver() {
        val gameObserver = Observer<GameData> { data ->
            result = data.result
            answeredQuestions = data.answeredQuestions
        }

        //Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)
    }
}