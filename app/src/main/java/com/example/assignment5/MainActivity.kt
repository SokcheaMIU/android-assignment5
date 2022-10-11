package com.example.assignment5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.walmart_login.*
import kotlinx.android.synthetic.main.walmart_shop.*

class MainActivity : AppCompatActivity() {
    private var users = arrayListOf<User>(
        User("Joe", lastName = "Cordon", username = "j@c.com", password = "123"),
        User("Anna", lastName = "Ave", username = "a@a.com", password = "111"),
        User("Ella", lastName = "Fallon", username = "e@f.com", password = "222"),
        User("Taylor", lastName = "Smith", username = "t@s.com", password = "321"),
        User("Justin", lastName = "Mella", username = "j@m.com", password = "333")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.walmart_login)
        signinBnt.setOnClickListener() {
            signIn()
        }

        forgotPassword.setOnClickListener() {
            sendEmail()
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val user: User? = it.data?.getSerializableExtra("newUser") as? User
                user?.let {
                    if (foundUser(user)) {
                        Toast.makeText(this, "Email address already exists", Toast.LENGTH_LONG).show()
                    } else {
                        users.add(user)
                        Toast.makeText(this, "Account created successfully.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        signupBnt.setOnClickListener() {
            val intent = Intent(this, SignupActivity::class.java)
            resultContracts.launch(intent)
        }
    }

    private fun foundUser(user: User) = users.find { it.username == user.username } != null

    private fun signIn() {
        val email: String = emailEditText.text.toString().trim()
        val pwd: String = passwordEditText.text.toString().trim()

        val user = users.find { u -> u.username == email && u.password == pwd }
        if (user == null) {
            var message = "Invalid Email address or password"
            if (email.isEmpty()) message = "Please enter Email address"
            if (pwd.isEmpty()) message = "Please enter Password"

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(this, ShoppingActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            passwordEditText.setText("")
        }
    }

    private fun sendEmail() {
        val recipient = emailEditText.text.toString().trim()
        if (recipient.isNotEmpty()) {
            val pwd: String = users.find {
                it.username == recipient
            }?.password ?: ""

            val subject = "Forgot Password"
            var message = "Email address not found"
            if (pwd.isNotEmpty()) message = "Your password is $pwd"

            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            mIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(mIntent, "Choose application to send Email...."))
        } else {
            Toast.makeText(this, "Please enter your Email address", Toast.LENGTH_LONG).show()
        }
    }
}