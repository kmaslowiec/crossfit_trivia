package com.example.android.crossfittrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class EmomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Change fragment title
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.emom_title)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emom, container, false)
    }
}