package com.example.loginpagekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Login klase
        val Login = Login()

        //Atrod elementus no layout un piesaista tos mainīgajiem
        val userNameInput = findViewById<EditText>(R.id.TextEmailAddress)
        val passwordInput = findViewById<EditText>(R.id.TextPassword)
        val btnLogin = findViewById<Button>(R.id.login_button)

        //Pogai uzliek onClickListener
        btnLogin.setOnClickListener{
            //Iegūst ievadīto tekstu no lietotājvārda un paroles teksta lodziņiem
            val user = userNameInput.text.toString()
            val password = passwordInput.text.toString()

            //Izsauc verifyUser no Login klases
            if (Login.verifyUser(this@MainActivity, user, password)){
                val intent = Intent(this, TVNetActivity::class.java)
                startActivity(intent)
            }
        }
    }

}