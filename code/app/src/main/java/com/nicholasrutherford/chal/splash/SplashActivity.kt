package com.nicholasrutherford.chal.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = SplashViewModel(applicationContext, this)
        binding.ivSplashLogo.setImageResource(viewModel.viewState.splashImageRes)
    }

}