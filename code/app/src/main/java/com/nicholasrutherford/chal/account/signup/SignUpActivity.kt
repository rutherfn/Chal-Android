package com.nicholasrutherford.chal.account.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivitySignupBinding
import com.nicholasrutherford.chal.signUpFragment
import com.nicholasrutherford.chal.navigationimpl.signup.SignUpNavigationImpl

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignupBinding? = null
    private val navigation =
        SignUpNavigationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadSignUpFragment()
    }

    private fun loadSignUpFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerSignUp,
            signUpFragment(this, applicationContext), signUpFragment(
                this,
                applicationContext
            ).javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigation.login(this)
    }
}