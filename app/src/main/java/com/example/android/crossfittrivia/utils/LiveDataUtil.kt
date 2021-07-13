package com.example.android.crossfittrivia.utils

import com.example.android.crossfittrivia.GameViewModel

class LiveDataUtil {

    companion object{
         fun resetStats(model : GameViewModel) {
            model.currentGame.value = GameStats(0, 0)
        }
    }
}