package com.example.android.crossfittrivia

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.databinding.FragmentGameBinding
import com.example.android.crossfittrivia.utils.*
import com.squareup.picasso.Picasso
import java.util.*

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var args: GameFragmentArgs
    private lateinit var questions: MutableList<Question>
    private lateinit var questionsTypeTwo: MutableList<Question>
    private lateinit var questionsTypeThree: MutableList<Question>

    //field that are used by DataBinding cannot be private
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var result = 0
    private var answeredQuestions = 0
    private var num: Long = 0
    private var tt: TimerTask? = null
    private val model: GameViewModel by activityViewModels()

    //setup number of questions
    private var questionsLimit = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args = GameFragmentArgs.fromBundle(requireArguments())
        setSharedPreferences()

        // Set DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        setObserver()

        gameMode(args.mode)

        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        initSubmitButton()

        setOrDisablePicture(currentQuestion)

        // Inflate the layout for this fragment
        return binding.root
    }

    // Set the onClickListener for the submitButton
    private fun initSubmitButton() {
        binding.submitGameButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            gameLogic(view)
        }
    }

    // Game logic for EMOM, AMRAP and Chipper modes
    private fun gameLogic(view: View) {
        val checkedId = binding.questionsRadioButton.checkedRadioButtonId
        // In EMOM mode submit button increases the number of questions
        if (args.mode == GameMode.EMOM) answeredQuestions++
        model.currentGame.value = GameStats(answeredQuestions, result)

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.second_answer_radiobutton -> answerIndex = 1
                R.id.third_answer_radiobutton -> answerIndex = 2
                R.id.fourth_answer_radiobutton -> answerIndex = 3
            }

            // CORRECT ANSWER
            // The first answer in the original question is always the correct one, so if our
            // answer matches, we have the correct answer.
            if (answers[answerIndex] == currentQuestion.answers[0]) {
                result++
                // In Chipper every answer increases number of the questions
                if (args.mode == GameMode.CHIPPER) answeredQuestions++
                model.currentGame.value = GameStats(answeredQuestions, result)

                // Advance to the next question
                if (answeredQuestions < questionsLimit) {

                    if (args.mode == GameMode.CHIPPER) {
                        Log.i("Number of question ", answeredQuestions.toString())
                        when (answeredQuestions) {
                            6 -> {
                                questions = questionsTypeTwo
                                randomizeQuestions()
                                Log.i("WHERE ", "In Second Type")
                            }
                            11 -> {
                                questions = questionsTypeThree
                                randomizeQuestions()
                                Log.i("WHERE ", "In Third Type")
                            }
                            else -> questions
                        }
                        Log.i("index ", questionIndex.toString())
                        uploadNextQuestion()
                    } else {
                        uploadNextQuestion()
                    }
                } else {
                    if (args.mode == GameMode.CHIPPER) {
                        cancelStopwatch()
                        view.findNavController().navigate(
                            GameFragmentDirections
                                .actionGameFragmentToResultsFragment(args.mode, num)
                        )
                    } else {
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultsFragment(args.mode))
                    }
                }
            } else {
                if (args.mode == GameMode.EMOM) view.findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToNoRepFragment
                        (currentQuestion.text)
                )
                // WRONG ANSWER
                if (args.mode == GameMode.CHIPPER) makeToast(getString(R.string.wrong_answer)) else
                    uploadNextQuestion()
            }
        }
    }

    // Randomize question before set them up
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    /* Sets the question and randomizes the answers.  This only changes the data, not the UI.
     * Calling invalidateAll on the FragmentGameBinding updates the data.*/
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }

    // Set SharedPreferences for num of question
    private fun setSharedPreferences() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.putInt("questions", questionsLimit)?.apply()
    }

    // Set Observer
    private fun setObserver() {
        val gameObserver = Observer<GameStats> { data ->
            result = data.result
            answeredQuestions = data.answeredQuestions
        }

        // Observe the LiveData
        model.currentGame.observe(activity as AppCompatActivity, gameObserver)
    }

    private fun timer() {
        var timePeriod: Long = secondsToMill(10)

        object : CountDownTimer(timePeriod, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timePeriod -= 1000L

                val time = TimerUtil.timerDisplay(millisUntilFinished)
                setModeTitle(getString(R.string.amrap_title) + time)
                prepToast(timePeriod)
            }

            override fun onFinish() {
                Navigation.findNavController(binding.root).navigate(GameFragmentDirections.actionGameFragmentToResultsFragment(args.mode))
            }
        }.start()
    }

    private fun stopwatch() {
        val timer = Timer()
        tt = object : TimerTask() {
            override fun run() {
                num += 1000L
                val runnable = Runnable { setModeTitle(getString(R.string.chipper_title) + TimerUtil.timerDisplay(num)) }
                activity?.runOnUiThread(runnable)
            }
        }
        timer.schedule(tt, 0L, 1000)
    }

    private fun cancelStopwatch() {
        tt?.cancel()
    }

    // Set the game mode
    private fun gameMode(gameMode: GameMode) {
        when (gameMode) {
            GameMode.AMRAP -> {
                questions = QuestionsList.questions
                questionsTypeTwo = Collections.emptyList()
                questionsTypeThree = Collections.emptyList()
                timer()
                makeToast(getString(R.string.amrap_entry_toast))
            }
            GameMode.EMOM -> {
                questions = QuestionsList.questions
                questionsTypeTwo = Collections.emptyList()
                questionsTypeThree = Collections.emptyList()
                setModeTitle(getString(R.string.emom_title, answeredQuestions + 1, questionsLimit))
                if (answeredQuestions == 0) makeToast(getString(R.string.emom_toast, questionsLimit))
            }
            GameMode.CHIPPER -> {
                questionsLimit = 15
                questions = QuestionsList.questions.filter { a -> a.type == QuestionType.TYPE_ONE }.toMutableList()
                questionsTypeTwo = QuestionsList.questions.filter { a -> a.type == QuestionType.TYPE_TWO }.toMutableList()
                questionsTypeThree = QuestionsList.questions.filter { a -> a.type == QuestionType.TYPE_THREE }.toMutableList()

                stopwatch()
                makeToast(getString(R.string.chipper_toast))
            }
        }
    }

    // Create and show Toast
    private fun makeToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    // Change fragment title
    private fun setModeTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    // Uploads next question
    private fun uploadNextQuestion() {
        questionIndex++
        if (args.mode == GameMode.CHIPPER) {
            if (questionsTypeTwo.size <= questionIndex) questionIndex = 0
            if (questionsTypeThree.size <= questionIndex) questionIndex = 0
        }

        currentQuestion = questions[questionIndex]
        setOrDisablePicture(currentQuestion)
        setQuestion()
        binding.invalidateAll()
    }

    private fun prepToast(timePeriod: Long) {
        when (timePeriod) {
            secondsToMill(40) -> makeToast(getString(R.string.amrap_4_min)) // change to 4 min
            secondsToMill(30) -> makeToast(getString(R.string.amrap_half_min)) // change to 2,5 min
            secondsToMill(20) -> makeToast(getString(R.string.amrap_1_min)) // change to 1 min
            secondsToMill(10) -> makeToast(getString(R.string.amrap_10_sec)) // change to 10 sec
            secondsToMill(5) -> makeToast(getString(R.string.amrap_3_sec)) // change to 3 sec
            secondsToMill(4) -> makeToast(getString(R.string.amrap_2_sec)) // change to 2 sec
            secondsToMill(3) -> makeToast(getString(R.string.amrap_1_sec)) // change to 1 sec
        }
    }

    private fun secondsToMill(seconds: Int): Long {
        return seconds * 1000L
    }

    private fun setOrDisablePicture(currentQuestion: Question) {
        when (currentQuestion.hasPic) {
            false -> {
                binding.questionImage.visibility = View.GONE
            }
            true -> {
                Picasso.with(activity)
                    .load(currentQuestion.picUrl)
                    .placeholder(R.drawable.loading)
                    .into(binding.questionImage)
            }
        }
    }
}