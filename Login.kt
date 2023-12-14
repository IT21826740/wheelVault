package com.example.appca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

                val signupButton: Button =findViewById(R.id.signUpBtn)
                signupButton.setOnClickListener {
                    val intent= Intent(this@Login,SignUp::class.java)
                    startActivity(intent)
                }

                val loginButton:Button=findViewById(R.id.loginBtn)
                loginButton.setOnClickListener {
                    val intent=Intent( this@Login,MainActivity::class.java)
                    startActivity(intent)
                }
    }
}
