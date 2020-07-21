package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.shaishavgandhi.loginbuttons.FacebookButton
import com.shaishavgandhi.loginbuttons.GoogleButton

class SignUpActivity: AppCompatActivity() {

    lateinit var tvSignUp: TextView
    lateinit var tvSubHeader: TextView
    lateinit var tvAlreadyHaveAccount: TextView
    lateinit var tvLogin: TextView
    lateinit var ivSignInLogo: ImageView
    lateinit var btnSignUpFacebook: FacebookButton
    private lateinit var btnSignUpPhone: Button
    lateinit var btnSignUpRegular: Button
    lateinit var ivTwitter: ImageView
    lateinit var ivFacebook: ImageView
    lateinit var ivGram: ImageView

    private var helper = Helper()
    private var typeface = Typeface()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        main()
    }

    private fun main() {
        setUpIds()
        setUpDisplay()
        regularListeners()
        socialListeners()
    }

    private fun setUpIds() {
        tvSubHeader = findViewById(R.id.tvSubHeader)
        ivSignInLogo = findViewById(R.id.ivSignInLogo)
        tvSignUp = findViewById(R.id.tvSignUp)
        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount)

        tvLogin = findViewById(R.id.tvLogin)

        btnSignUpFacebook = findViewById(R.id.btnSignUpFacebook)
        btnSignUpPhone = findViewById(R.id.btnSignUpPhone)
        btnSignUpRegular = findViewById(R.id.btnSignUpRegular)

        ivTwitter = findViewById(R.id.ivTwitter)
        ivFacebook = findViewById(R.id.ivFacebook)
        ivGram = findViewById(R.id.ivGram)
    }

    private fun setTypefaces() {
        typeface.setTypefaceForHeaderBold(tvSignUp, baseContext)
        typeface.setTypefaceForHeaderRegular(tvSubHeader, baseContext)

        typeface.setTypefaceForBodyRegular(tvAlreadyHaveAccount, baseContext)
        typeface.setTypefaceForBodyBold(tvLogin, baseContext)
    }

    private fun setTextColor() { // down the line for dark mode functionality, this will turn into a more complex method.
        helper.setTextViewColor(applicationContext, tvSignUp, R.color.colorPrimary)
        helper.setTextViewColor(applicationContext, tvSubHeader, R.color.colorBlack)

        helper.setTextViewColor(applicationContext, tvAlreadyHaveAccount, R.color.colorBlack)
        helper.setTextViewColor(applicationContext, tvLogin, R.color.colorBlue)
    }

    private fun setTextForTextViews() {
        tvSignUp.setText(R.string.sign_up)
        tvSubHeader.setText(R.string.sign_up_slogan_message)
        tvAlreadyHaveAccount.setText(R.string.already_have_a_account_text)
        tvLogin.setText(R.string.login_text)
    }

    private fun setUpDisplay() {
        setTypefaces()
        setTextColor()

        setTextForTextViews()
    }

    private fun regularListeners() {

        tvLogin.setOnClickListener {
            sendUserToLogin()
        }

        btnSignUpRegular.setOnClickListener {
            sendUserToCreateAccount()
        }

        btnSignUpPhone.setOnClickListener {
            sendUserToAskForPhoneNumber()
        }


    }

    private fun socialListeners() {
        ivGram.setOnClickListener {
            sendUserToSocial(isGram = true, isFacebook = false, isTwitter = false)
        }

        ivFacebook.setOnClickListener {
            sendUserToSocial(isGram = false, isFacebook = true, isTwitter = false)
        }

        ivTwitter.setOnClickListener {
            sendUserToSocial(isGram = false, isFacebook = false, isTwitter = true)
        }

    }

    private fun sendUserToSocial(isGram: Boolean, isFacebook: Boolean, isTwitter: Boolean) {
        val intent = Intent(this@SignUpActivity, SocialMediaWebActivity::class.java)
        intent.putExtra("isGram", isGram)
        intent.putExtra("isFacebook", isFacebook)
        intent.putExtra("isTwitter", isTwitter)
        startActivity(intent)
    }

    private fun sendUserToLogin() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun sendUserToCreateAccount() {
        val intent = Intent(applicationContext, CreateAccountActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun sendUserToAskForPhoneNumber() {
        val intent = Intent(applicationContext, AskForPhoneNumberActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun startLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)

        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        startLoginActivity()
        super.onBackPressed()
    }

}