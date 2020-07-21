package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.activitys.accounts.SignUpActivity

class SplashActivity : AppCompatActivity() {

    // declarations
    private lateinit var ivSplash: ImageView
    private var mAuth: FirebaseAuth? = null

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
        mAuth = FirebaseAuth.getInstance()
        ivSplash = findViewById(R.id.ivSplashLogo)
    }

    private fun setSplashImage() {
        ivSplash.setImageResource(R.drawable.primary_logo)
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
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
            checkIfUserIsSignedInOrNot()
        }, 5000)
    }

    private fun checkIfUserIsSignedInOrNot() {
        val currentUser = mAuth!!.currentUser

        if(currentUser == null) {
            startLoginActivity()
        } else {
            startMainActivity()
        }

    }

}