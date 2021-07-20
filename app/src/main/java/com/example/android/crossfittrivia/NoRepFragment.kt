package com.example.android.crossfittrivia

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android.crossfittrivia.databinding.FragmentNoRepBinding
import com.example.android.crossfittrivia.utils.*

class NoRepFragment : Fragment() {

    private lateinit var binding: FragmentNoRepBinding
    private val model: GameViewModel by activityViewModels()
    private var answeredQuestions = 0
    private lateinit var timer: CountDownTimer
    private val lessons: MutableList<Lesson> = LessonList.lessons
    private var questions: MutableList<Question> = QuestionsList.questions

    //Custom Back Button
    //Deletes all stats timer and stopwatch when back button is pressed
    //this happens before on create
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //create listener for yes/no dialog
        val dialogClickListener : DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    LiveDataUtil.resetStats(model)
                    NavHostFragment.findNavController(requireParentFragment()).navigateUp()
                    MessageUtil.makeToast(getString(R.string.all_stats_gone), requireActivity())
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    return@OnClickListener
                }
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                LiveDataUtil.resetStats(model)
                MessageUtil.backButtonDialog(context, dialogClickListener)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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

        val args = NoRepFragmentArgs.fromBundle(requireArguments())

        val currentLesson = lessons.find { a -> a.question == args.question }

        when (currentLesson?.hasVideo) {
            null -> {
                binding.video.visibility = View.GONE
                binding.explText.visibility = View.GONE
                binding.titleText.text = resources.getString(R.string.lesson_not_found)
            }
            true -> {
                binding.explText.visibility = View.GONE
                binding.titleText.text = currentLesson.content
                webVideo(currentLesson.url)
            }
            else -> {
                binding.video.visibility = View.GONE
                binding.explText.text = currentLesson.content
            }
        }
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

                binding.returnButton.text = TimerUtil.timerDisplay(millisUntilFinished)
            }

            override fun onFinish() {
                if (!isAdded) return
                binding.returnButton.text = resources.getString(R.string.return_button)
                binding.returnButton.setOnClickListener {
                    if (answeredQuestions < getQuestionLimit()) {
                        view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToEmomFragment(GameMode.EMOM))
                    } else {
                        view?.findNavController()?.navigate(NoRepFragmentDirections.actionNoRepFragmentToResultsFragment())
                    }
                }
            }
        }.start()
    }

    // Init WebVideo
    @SuppressLint("SetJavaScriptEnabled")
    private fun webVideo(url: String) {
        val myWebVideo: WebView = binding.video

        val webSettings: WebSettings = myWebVideo.settings
        webSettings.javaScriptEnabled = true
        myWebVideo.loadUrl(url)
    }
}