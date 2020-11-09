package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.LoginActivity
import com.nicholasrutherford.chal.fragments.errorCreateAccountDialog
import com.nicholasrutherford.chal.fragments.loadingDialog
import com.nicholasrutherford.chal.helpers.Helper
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

    private lateinit var tvEmailErrorForgotPassword: TextView

    private var typeface = Typeface()
    private var helper = Helper()

    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_activity)
        main()
    }

    private fun main() {
        setupView()

        etEditorActionListeners()

        checkIfEmailIsEnteredCorrectly()

        doneListener()
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

        tvEmailErrorForgotPassword = findViewById(R.id.tvErrorEmailForgotPassword)
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
        typeface.setTypefaceForBodyBold(tvEmailErrorForgotPassword, baseContext)
    }

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorEmail() {
        tvEmailErrorForgotPassword.visibility = View.VISIBLE
    }

    private fun dismissErrorEmail() {
        tvEmailErrorForgotPassword.visibility = View.GONE
    }

    private fun isEmailError(): Boolean {
        return tvEmailErrorForgotPassword.visibility == View.VISIBLE
    }

    private fun checkIfEmailIsEnteredCorrectly() {
        etTypeEmail.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val email = etTypeEmail.text.toString()

                if(email.contains("@") && email.contains(".com")) {
                    dismissErrorEmail()
                } else if(email == "") {
                    dismissErrorEmail()
                } else {
                    showErrorEmail()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun etEditorActionListeners() {

        etTypeEmail.setOnEditorActionListener { _, actionId, _ ->
            val email = etTypeEmail.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
                attemptToSendResetPassword()
            } else if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }

    }

    private fun doneListener() {

        btDone.setOnClickListener {
            attemptToSendResetPassword()
        }

    }

    private fun attemptToSendResetPassword() {

        val resetEmail = etTypeEmail.text.toString()

        if(isEmailError()) {
            errorCreateAccountDialog.show(fm, "errorResetDialog")
        }

        else {

            loadingDialog.show(fm, "loadingDialog")

            val auth = FirebaseAuth.getInstance()

            auth.sendPasswordResetEmail(resetEmail)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful) {

                        updateView()
                        loadingDialog.dismiss()
                    }
                }
        }
    }

    private fun updateView() {

        etTypeEmail.visibility = View.GONE
        btDone.visibility = View.GONE

        tvForgotPassword.text = getString(R.string.email_sent)
        tvPleasEnterYour.text = getString(R.string.please_check_email)
        tvToResetPassword.text = getString(R.string.to_reset_password)
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