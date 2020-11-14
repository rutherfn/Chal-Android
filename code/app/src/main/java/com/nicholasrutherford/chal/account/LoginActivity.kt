package com.nicholasrutherford.chal.account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityLoginBinding
import com.nicholasrutherford.chal.fragments.loginFragment

class LoginActivity : AppCompatActivity() {
    private val fm = supportFragmentManager

    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        main(binding!!)
    }

    private fun main(binding: ActivityLoginBinding) {
        loadLoginFragment()
    }

    private fun loadLoginFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerLogin, loginFragment(this,applicationContext), loginFragment(this,applicationContext).javaClass.simpleName)
            .commit()
    }

}