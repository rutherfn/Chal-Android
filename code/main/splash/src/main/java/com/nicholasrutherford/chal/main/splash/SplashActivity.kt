package com.nicholasrutherford.chal.main.splash

import android.os.Bundle
import com.nicholasrutherford.chal.main.splash.databinding.ActivitySplashBinding

class SplashActivity : androidx.appcompat.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}