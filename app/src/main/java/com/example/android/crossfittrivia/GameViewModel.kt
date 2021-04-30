package com.example.android.crossfittrivia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.crossfittrivia.data.GameData

class GameViewModel : ViewModel() {

    val currentGame: MutableLiveData<GameData> by lazy { MutableLiveData<GameData>() }
}