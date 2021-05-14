package com.example.android.crossfittrivia.data

class QuestionsList {

    companion object {
        val questions: MutableList<Question> = mutableListOf(
            Question(text = "AMRAP means:", answers = listOf("As Many Reps As Possible", "All Man Rest And Pause", "As Many Rest As Possible", "Any" +
                    " Male Rolls And Push")),
            Question(text = "BP means", answers = listOf("Bench Press", "Burpess Push", "Bank Press", "Bio Propan")),
            Question(text = "BS means", answers = listOf("Back Squat", "Burpee Start", "Board Snatch", "Bar Snack")),
            Question(text = "FS means", answers = listOf("Front Squat", "Front Step", "First Slide", "Fun Song")),
            Question(text = "CFWU means", answers = listOf("CrossFit Warm-up", "CrossFit WORD Under", "CrossFit Weight Under", "CrossFit Water Unlimitted")),
            Question(text = "CLN means", answers = listOf("Clean", "Cross Line Net", "Count Low Nest", "Close Night")),
            Question(text = "C&J", answers = listOf("Clean & Jerk", "Cat & Jerry", "Coffee & Jelly", "Clear & Joyfull")),
            Question(text = "CTB means", answers = listOf("Chest to the Bar Pullups", "Clean Towar Bar", "Clear The Box", "Count The Boxes")),
            Question(text = "DL means", answers = listOf("Deadlift", "Down Legs", "Don't Lower", "Destroy Legs")),
            Question(text = "DU means", answers = listOf("Double Unders", "Down Urology", "Don't Understand", "Double Unite")))
    }
}