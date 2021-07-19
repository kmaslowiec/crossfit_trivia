package com.example.android.crossfittrivia.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.android.crossfittrivia.GameViewModel

class LiveDataUtil {

    companion object{
         fun resetStats(model : GameViewModel) {
            model.currentGame.value = GameStats(0, 0)
        }

        private fun makeToast(text: String, activity : FragmentActivity) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }
}