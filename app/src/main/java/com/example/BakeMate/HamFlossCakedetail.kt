package com.example.BakeMate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class HamFlossCakedetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hamflosscake_detail)

        val startBtn = findViewById<ImageView>(R.id.detailarrow001)
        startBtn.setOnClickListener{
            val myintent = Intent(this,MainActivity::class.java)
            startActivity(myintent)
        }
    }
}