package com.example.BakeMate

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.annotation.RequiresApi


class Intro : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val startBtn = findViewById<ConstraintLayout>(R.id.startbtn)
        startBtn.setOnClickListener{
            val myintent = Intent(this, FadeActivity::class.java)
            startActivity(myintent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}

