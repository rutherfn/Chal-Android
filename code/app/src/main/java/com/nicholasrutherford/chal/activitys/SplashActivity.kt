package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R

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
        ivSplash.setImageResource(R.drawable.placeholder)
    }

    private fun startMainActivity() {
        val intent: Intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun timerForNewActivity() {
        val handler = Handler()
        handler.postDelayed({
            startMainActivity()
        }, 5000)
    }

}