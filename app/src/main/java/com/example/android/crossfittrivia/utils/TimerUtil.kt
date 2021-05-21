package com.example.android.crossfittrivia.utils

import java.util.concurrent.TimeUnit

class TimerUtil {
    companion object {
        fun timerDisplay(time: Long): String {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(time)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit
                .MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))

            return " %02d:%02d".format(minutes, seconds)
        }
    }
}