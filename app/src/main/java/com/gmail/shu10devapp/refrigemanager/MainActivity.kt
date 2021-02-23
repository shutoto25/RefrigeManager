package com.gmail.shu10devapp.refrigemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mLoginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLoginButton = findViewById(R.id.login_button)
        setClickListeners()
    }

    /**
     * set listeners.
     */
    private fun setClickListeners() {

        mLoginButton.setOnClickListener { _->
            val intent = Intent(this, TabContentsActivity::class.java)
            startActivity(intent)
        }
    }

}