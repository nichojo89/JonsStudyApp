package com.nicholssoftware.jonsstudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nicholssoftware.jonsstudyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
    }
}