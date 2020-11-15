package com.nicholasrutherford.chal.account.createaccount

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityCreateAccountBinding
import com.nicholasrutherford.chal.fragments.createAccountFragment
import com.nicholasrutherford.chal.navigationimpl.CreateAccountNavigationImpl

class CreateAccountActivity : AppCompatActivity() {
    private val fm = supportFragmentManager
    private var binding: ActivityCreateAccountBinding? = null
    private var navigation = CreateAccountNavigationImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadCreateAccountFragment()
    }

    private fun loadCreateAccountFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.containerCreateAccount, createAccountFragment(this, applicationContext),
        createAccountFragment(this, applicationContext).javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigation.login(this)
    }

}