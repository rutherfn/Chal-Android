package com.nicholasrutherford.chal.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.databinding.ActivitySplashBinding
import com.nicholasrutherford.chal.viewmodels.SplashViewModel

class SplashActivity : AppCompatActivity() {

    // declarations
    private var mAuth: FirebaseAuth? = null
    private var viewModel: SplashViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        main(binding)
    }

    private fun main(binding: ActivitySplashBinding) {
        viewModel = SplashViewModel(applicationContext)
        mAuth = FirebaseAuth.getInstance()
        setSplashImage(binding)
        timerForNewActivity()
    }

    private fun setSplashImage(binding: ActivitySplashBinding) {
       // binding.ivSplashLogo.setImageResource(viewModel?.viewState?.splashImageRes)
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