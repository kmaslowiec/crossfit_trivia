package com.example.android.crossfittrivia.utils

class QuestionsList {

    companion object {
        val questions: MutableList<Question> = mutableListOf(
            Question(
                text = "AMRAP means:", answers = listOf(
                    "As Many Reps As Possible", "All Man Rest And Pause", "As Many Rest As Possible", "Any" +
                            " Male Rolls And Push"
                )
            ),
            Question(text = "BP means", answers = listOf("Bench Press", "Burpess Push", "Bank Press", "Bio Propan")),
            Question(text = "BS means", answers = listOf("Back Squat", "Burpee Start", "Board Snatch", "Bar Snack")),
            Question(text = "FS means", answers = listOf("Front Squat", "Front Step", "First Slide", "Fun Song")),
            Question(
                text = "CFWU means",
                answers = listOf("CrossFit Warm-up", "CrossFit WORD Under", "CrossFit Weight Under", "CrossFit Water Unlimitted")
            ),
            Question(text = "CLN means", answers = listOf("Clean", "Cross Line Net", "Count Low Nest", "Close Night")),
            Question(
                text = "C&J TYPE TW0", answers = listOf("Clean & Jerk", "Cat & Jerry", "Coffee & Jelly", "Clear & Joyfull"), QuestionType
                    .TYPE_TWO
            ),
            Question(
                text = "CTB means TYPE TW0", answers = listOf("Chest to the Bar Pullups", "Clean Towar Bar", "Clear The Box", "Count The Boxes"),
                QuestionType.TYPE_TWO
            ),
            Question(
                text = "DL means TYPE TW0", answers = listOf("Deadlift", "Down Legs", "Don't Lower", "Destroy Legs"),
                QuestionType.TYPE_TWO
            ),
            Question(
                text = "DU means TYPE TWO", answers = listOf("Double Unders", "Down Urology", "Don't Understand", "Double Unite"), QuestionType
                    .TYPE_TWO
            ),
            Question(text = "BS means TYPE TWO", answers = listOf("Back Squat", "Burpee Start", "Board Snatch", "Bar Snack"), QuestionType.TYPE_TWO),
            Question(text = "FS means TYPE TWO", answers = listOf("Front Squat", "Front Step", "First Slide", "Fun Song"), QuestionType.TYPE_TWO),
            Question(
                text = "What is at the pic?",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            ),
            Question(
                text = "Second Type Three",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            ),
            Question(
                text = "Third Type Three",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            ),
            Question(
                text = "Third Type Three",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            ),
            Question(
                text = "Fourth Type Three",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            ),
            Question(
                text = "Fifth Type Three",
                answers = listOf("Squat", "Under", "Jerry", "Jump"), QuestionType.TYPE_THREE, true,
                "https://drive.google.com/uc?id=18tAwDu9E0m7_yG_nEvPEi-K_1atiKUxd"
            )
        )
    }
}