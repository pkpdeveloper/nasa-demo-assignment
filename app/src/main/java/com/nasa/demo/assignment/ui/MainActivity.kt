package com.nasa.demo.assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nasa.demo.assignment.R
import com.nasa.demo.assignment.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, MainFragment())
            .commit()
    }
}