package com.mws.travelapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLoginreal : Button
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginreal = findViewById(R.id.btnLogin_real)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etEmail)

        btnLoginreal.setOnClickListener {
            val email = 2
            if (etEmail.equals("kelompok mwsp") && etPassword.equals("123456")){
                var progressDialog = ProgressDialog(this, R.style.Theme_MaterialComponents_Light_Dialog)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Proses")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}