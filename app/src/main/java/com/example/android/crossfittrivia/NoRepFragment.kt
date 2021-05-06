package com.example.android.crossfittrivia

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.data.GameStats
import com.example.android.crossfittrivia.databinding.FragmentNoRepBinding
import java.util.concurrent.TimeUnit

class NoRepFragment : Fragment() {

    private lateinit var binding: FragmentNoRepBinding
    private val model: GameViewModel by activityViewModels()
    private var answeredQuestions = 0
    private lateinit var timer: CountDownTimer


    @SuppressLint("SetJavaScriptEnabled")
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

        val myWebVideo : WebView = binding.video

        val webSettings : WebSettings = myWebVideo.settings
        webSettings.javaScriptEnabled = true

        myWebVideo.loadUrl("https://www.youtube.com/embed/TU8QYVW0gDU")
        return binding.root
    }

    //Get questionLimit from SharedPreferences
    private fun getQuestionLimit(): Int {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPref?.getInt("questions", 0)!!
    }

    // Set Observer
    private fun setObserver() {
        val gameObserver = Observer<GameStats> { data ->
            answeredQuestions = data.answeredQuestions
        }

        //Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)
    }

    // Init returnButton
    private fun returnButton() {
        timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit
                    .MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))

                val time = "%02d:%02d".format(minutes, seconds)

                binding.returnButton.text = time
            }

            override fun onFinish() {
                binding.returnButton.text = resources.getString(R.string.return_button)
                binding.returnButton.setOnClickListener {
                    if (answeredQuestions < getQuestionLimit()) {
                        view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToEmomFragment())
                    } else {
                        view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToResultsFragment())
                    }
                }
            }
        }.start()
    }
}