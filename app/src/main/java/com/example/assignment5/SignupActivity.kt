package com.example.assignment5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.walmart_login.*
import kotlinx.android.synthetic.main.walmart_login.signupBnt
import kotlinx.android.synthetic.main.walmart_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.walmart_signup)

        signupBnt.setOnClickListener() {
            val fName = editTextFirstName.text.toString().trim()
            val lName = editTextLastName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val pwd = editTextPassword.text.toString().trim()

            if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
                if (fName.isEmpty())
                    editTextFirstName.error = "Please enter First name"

                if (lName.isEmpty())
                    editTextLastName.error = "Please enter Last name"

                if (email.isEmpty())
                    editTextEmail.error = "Please enter Email address"

                if (pwd.isEmpty())
                    editTextPassword.error = "Please enter Password"
            } else {
                val user = User(fName, lName, email, pwd)
                val intent = Intent()
                intent.putExtra("newUser", user)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}