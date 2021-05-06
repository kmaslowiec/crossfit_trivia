package com.example.android.crossfittrivia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.crossfittrivia.data.GameStats

class GameViewModel : ViewModel() {

    val currentGame: MutableLiveData<GameStats> by lazy { MutableLiveData<GameStats>() }
}