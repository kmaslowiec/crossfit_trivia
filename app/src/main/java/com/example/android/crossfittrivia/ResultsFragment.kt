package com.example.android.crossfittrivia

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
import com.example.android.crossfittrivia.databinding.FragmentResultsBinding
import com.example.android.crossfittrivia.utils.GameMode
import com.example.android.crossfittrivia.utils.GameStats
import com.example.android.crossfittrivia.utils.TimerUtil

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding
    private lateinit var args: ResultsFragmentArgs

    private var answeredQuestions: Int = 0
    private var result: Int = 0
    private val model: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.results_title)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)
        args = ResultsFragmentArgs.fromBundle(requireArguments())
        setObserver()

        // Show results in the scoreText view
        if (args.mode == GameMode.CHIPPER) {
            binding.scoreText.text = resources.getString(R.string.score_time, TimerUtil.timerDisplay(args.time))
        } else {
            binding.scoreText.text = resources.getString(R.string.score_text, result)
        }

        initPlayAgainButton()
        initChooseGameButton()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        resetStats()
    }

    // Set Observer
    private fun setObserver() {
        val gameObserver = Observer<GameStats> { data ->
            result = data.result
            answeredQuestions = data.answeredQuestions
        }

        //Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)
    }

    // Init the PlayAgain button
    private fun initPlayAgainButton() {
        binding.againButton.setOnClickListener { view ->
            resetStats()
            Log.i("MODE IN RESULT ", args.mode.toString())
            view.findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToEmomFragment(args.mode))
        }
    }

    // Init the chooseGameButton button
    private fun initChooseGameButton() {
        binding.chooseGameButton.setOnClickListener { view ->
            resetStats()
            view.findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToChoiceFragment())
        }
    }

    // Reset stats for new game
    private fun resetStats() {
        model.currentGame.value = GameStats(0, 0)
    }
}