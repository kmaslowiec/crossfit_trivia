package com.example.android.crossfittrivia

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.android.crossfittrivia.data.GameStats
import com.example.android.crossfittrivia.data.Question
import com.example.android.crossfittrivia.data.QuestionsList
import com.example.android.crossfittrivia.databinding.FragmentEmomBinding

class EmomFragment : Fragment() {

    private lateinit var binding: FragmentEmomBinding
    private var questions: MutableList<Question> = QuestionsList.questions

    //field that are used by DataBinding cannot be private
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var result = 0
    private var answeredQuestions = 0
    private val model: GameViewModel by activityViewModels()

    //setup number of questions
    private val numQuestions = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Change fragment title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.emom_title)

        setSharedPreferences()

        // Set DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emom, container, false)

        setObserver()

        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        initSubmitButton()

        // Inflate the layout for this fragment
        return binding.root
    }

    // Set the onClickListener for the submitButton
    private fun initSubmitButton() {
        binding.submitGameButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionsRadioButton.checkedRadioButtonId
            answeredQuestions++
            model.currentGame.value = GameStats(answeredQuestions, result)

            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.second_answer_radiobutton -> answerIndex = 1
                    R.id.third_answer_radiobutton -> answerIndex = 2
                    R.id.fourth_answer_radiobutton -> answerIndex = 3
                }

                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    result++

                    model.currentGame.value = GameStats(answeredQuestions, result)

                    // Advance to the next question
                    if (answeredQuestions < numQuestions) {

                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(EmomFragmentDirections.actionEmomFragmentToResultsFragment())
                    }
                } else {
                    view.findNavController().navigate(EmomFragmentDirections.actionEmomFragmentToNoRepFragment())
                }
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
        sharedPref?.edit()?.putInt("questions", numQuestions)?.apply()
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
}