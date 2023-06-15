package com.example.BakeMate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PlaidBreaddetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plaidbread_detail)

        val startBtn = findViewById<ImageView>(R.id.detailarrow1)
        startBtn.setOnClickListener{
            val myintent = Intent(this,MainActivity::class.java)
            startActivity(myintent)
        }
    }
}