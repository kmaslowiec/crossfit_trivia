package com.example.android.crossfittrivia.utils

class LessonList {

    companion object {
        val lessons: MutableList<Lesson> = mutableListOf(
            Lesson(
                false,
                "AMRAP means:",
                "An AMRAP session focuses on pushing yourself as much as possible during a set time frame; it can form part of a bigger routine (a 10-minute AMRAP 'burn' at the end, for example) or it could be your whole workout.",
                ""
            ),
            Lesson(true, "BP means", "Bench Press (BP)", "https://www.youtube.com/embed/XSza8hVTlmM"),
            Lesson(true, "BS means", "Back Squat (BS)", "https://www.youtube.com/embed/QmZAiBqPvZw"),
            Lesson(true, "FS means", "Front Squad (FS)", "https://www.youtube.com/embed/uYumuL_G_V0"),
            Lesson(
                false,
                "CFWU means",
                "CrossFit Warm UP: Warm muscles perform better and faster in both the concentric and eccentric motion, as opposed to cold muscles. Giving you more power and strength while decreasing the chance of injury. By creating a light sweat it will prep your body to cool more quickly once the work begins and strain on the heart will be decreased by the ability of the blood to move more rapidly.",
                ""
            ),
            Lesson(true, "CLN means", "Clean (CLN)", "https://www.youtube.com/embed/EKRiW9Yt3Ps"),
            Lesson(true, "C&J", "Clean & Jerk (C&J)", "https://www.youtube.com/embed/PjY1rH4_MOA"),
            Lesson(true, "CTB means", "Chest to the bar pull-ups (CTB)", "https://www.youtube.com/embed/AyPTCEXTjOo"),
            Lesson(true, "DL means", "Deadlift (DL)", "https://www.youtube.com/embed/1ZXobu7JvvE"),
            Lesson(true, "DU means", "Double Unders (DU)", "https://www.youtube.com/embed/82jNjDS19lg")
        )
    }
}