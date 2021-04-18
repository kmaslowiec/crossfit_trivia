package com.example.android.crossfittrivia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class NoRepFragment : Fragment() {

    lateinit var args: NoRepFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        args = NoRepFragmentArgs.fromBundle(requireArguments())

        Log.i("CURRENT QUESTION", args.currentQuestion.toString())
        Log.i("CURRENT RESULT", args.result.toString())

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.no_rep_title)

        return inflater.inflate(R.layout.fragment_no_rep, container, false)
    }
}