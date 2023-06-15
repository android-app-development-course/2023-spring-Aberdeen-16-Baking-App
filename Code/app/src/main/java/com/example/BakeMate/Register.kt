package com.example.BakeMate

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val checkBox2 = findViewById<CheckBox>(R.id.checkBox22)
        val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword2)
        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editTextTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                editTextTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        val registerBtn = findViewById<Button>(R.id.registerBtn2)
        registerBtn.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
            val password = findViewById<EditText>(R.id.editTextTextPassword2).text.toString()
            val phone = findViewById<EditText>(R.id.editTextTextMobile3).text.toString()

            val intent = Intent()
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            intent.putExtra("phone", phone)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}