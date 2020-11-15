package com.nicholasrutherford.chal.account.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityLoginBinding
import com.nicholasrutherford.chal.fragments.loginFragment

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadLoginFragment()
    }

    private fun loadLoginFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerLogin, loginFragment(this,applicationContext),
            loginFragment(this,applicationContext).javaClass.simpleName)
            .commit()
    }

}