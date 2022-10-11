package com.example.assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.walmart_shop.*

class ShoppingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.walmart_shop)

        val user = intent.getSerializableExtra("user") as User
        val username = user.username
        welcomeTextView.text = "Welcome $username"

        bntCone.setOnClickListener(this)
        bntGlass.setOnClickListener(this)
        bntWooden.setOnClickListener(this)
        bntSprinkle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var cateName = ""
        when(v?.id){
            R.id.bntCone -> cateName = tvCone.text.toString()
            R.id.bntGlass -> cateName = tvGlass.text.toString()
            R.id.bntWooden -> cateName = tvWoodenStick.text.toString()
            R.id.bntSprinkle -> cateName = tvSprinkle.text.toString()
        }

        Toast.makeText(this, "You have chosen $cateName category of shopping", Toast.LENGTH_LONG).show()
    }
}