package com.example.android.crossfittrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.crossfittrivia.data.GameData

class GameViewModel : ViewModel() {

    val currentResult : MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

}


