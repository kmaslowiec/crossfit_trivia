package com.example.android.crossfittrivia.data

class QuestionsList {

    companion object {
        val questions: MutableList<Question> = mutableListOf(
            Question(
                text = "What is Android Jetpack?",
                answers = listOf("all of these", "tools", "documentation", "libraries")
            ),
            Question(
                text = "Base class for Layout?",
                answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
            ),
            Question(
                text = "Layout for complex Screens?",
                answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
            ),
            Question(
                text = "Pushing structured data into a Layout?",
                answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
            ),
            Question(
                text = "Inflate layout in fragments?",
                answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")
            ),
            Question(
                text = "Build system for Android?",
                answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
            ),
            Question(
                text = "Android vector format?",
                answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")
            ),
            Question(
                text = "Android Navigation Component?",
                answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
            ),
            Question(
                text = "Registers app with launcher?",
                answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
            ),
            Question(
                text = "Mark a layout for Data Binding?",
                answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
            )
        )
    }

}