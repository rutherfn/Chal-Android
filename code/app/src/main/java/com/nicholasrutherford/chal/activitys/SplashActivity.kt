package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.activitys.accounts.SignUpActivity

class SplashActivity : AppCompatActivity() {

    // declarations
    private lateinit var ivSplash: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        main()
    }

    private fun main() {
        setupIds()
        setSplashImage()
        timerForNewActivity()
    }

    private fun setupIds() {
        ivSplash = findViewById(R.id.ivSplashLogo)
    }

    private fun setSplashImage() {
        ivSplash.setImageResource(R.drawable.primary_logo)
    }

    private fun startSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun timerForNewActivity() {
        val handler = Handler()
        handler.postDelayed({
            startLoginActivity()
        }, 5000)
    }

}