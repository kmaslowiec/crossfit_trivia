package com.example.android.crossfittrivia.data

enum class QUESTIONS(question: Question) {
    ONE(
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")
        )
    ),
    TWO(
        Question(
            text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        )
    ),
    THREE(
        Question(
            text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        )
    ),
    FOUR(
        Question(
            text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
        )
    ),
    FIVE(
        Question(
            text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")
        )
    ),
    SIX(
        Question(
            text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        )
    ),
    SEVEN(
        Question(
            text = "Android vector format?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")
        )
    ),
    EIGHT(
        Question(
            text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        )
    ),
    NINCE(
        Question(
            text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        )
    ),
    TEN(
        Question(
            text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )
}