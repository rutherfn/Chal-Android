package com.nicholasrutherford.chal.account.forgotpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityForgotPasswordBinding
import com.nicholasrutherford.chal.forgotPasswordFragment

class ForgotPasswordActivity : AppCompatActivity() {
    private var binding: ActivityForgotPasswordBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadForgotPasswordFragment()
    }

    private fun loadForgotPasswordFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerForgotPassword,
        forgotPasswordFragment(this, applicationContext),
        forgotPasswordFragment(this, applicationContext).javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        forgotPasswordFragment(this, applicationContext).viewModel.navigateToLogin()
    }

}