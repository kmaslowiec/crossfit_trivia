package com.example.android.crossfittrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setContentView(R.layout.activity_main)
    }
}