package com.example.android.crossfittrivia.utils

data class Question(
    var text: String, var answers: List<String>, var type: QuestionType = QuestionType.TYPE_ONE, var hasPic: Boolean = false, var
    picUrl: String = ""
)
