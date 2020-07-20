package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Typeface

class ForgotPasswordActivity : AppCompatActivity() {

    // declarations
    private lateinit var tbForgotPassword: Toolbar
    private lateinit var ivForgotPassword: ImageView
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvPleasEnterYour: TextView
    private lateinit var tvToResetPassword: TextView
    private lateinit var btDone: Button
    private lateinit var etTypeEmail: EditText

    private var typeface = Typeface()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_activity)
        main()
    }

    private fun main() {
        setupView()
    }

    private fun setupView() {
        setupIds()
        setupToolbar()
        setUpTypefaces()
    }

    private fun setupIds() {

        tbForgotPassword = findViewById(R.id.tbForgotPassword)
        ivForgotPassword = findViewById(R.id.ivForgotPassword)
        tvForgotPassword = findViewById(R.id.tvForgotYourPassword)

        tvPleasEnterYour = findViewById(R.id.tvPleaseEnterYour)
        tvToResetPassword = findViewById(R.id.tvToResetPassword)

        btDone = findViewById(R.id.btDone)
        etTypeEmail = findViewById(R.id.etTypeEmail)
    }

    private fun setupToolbar() {
        setSupportActionBar(tbForgotPassword)

        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpTypefaces() {
        typeface.setTypefaceForHeaderBold(tvForgotPassword, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvPleasEnterYour, baseContext)
        typeface.setTypefaceForSubHeaderBold(tvToResetPassword, baseContext)
        typeface.setTypefaceForBodyBold(btDone, baseContext)
    }

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if(item.itemId == android.R.id.home) {
            startLoginActivity()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        startLoginActivity()
        super.onBackPressed()
    }

}