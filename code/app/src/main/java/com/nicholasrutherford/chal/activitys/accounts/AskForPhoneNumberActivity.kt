package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import java.util.concurrent.TimeUnit

class AskForPhoneNumberActivity : AppCompatActivity() {

    // declarations
    private lateinit var ivPhoneHeader: ImageView
    private lateinit var tvMayIAsk: TextView
    private lateinit var etPhoneNumber: EditText
    private lateinit var ivPhoneNumber: ImageView
    private lateinit var btnContinuePhone: Button

    private val helper = Helper()
    private val typeface = Typeface()

    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        main()
    }

    private fun main() {
        setupView()
        continueClickListener()
    }

    private fun setupView() {
        setupIds()
        setTypefaceAndTextViewsColors()
    }

    private fun setupIds() {
        ivPhoneHeader = findViewById(R.id.ivPhoneHeader)
        tvMayIAsk = findViewById(R.id.tvMayIAsk)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        ivPhoneNumber = findViewById(R.id.ivPhoneNumber)
        btnContinuePhone = findViewById(R.id.btnContinuePhone)
    }

    private fun setTypefaceAndTextViewsColors() {

        // typeface
        typeface.setTypefaceForHeaderBold(tvMayIAsk, baseContext)
        typeface.setTypefaceForSubHeaderBold(btnContinuePhone, baseContext)

        // text view colors
        helper.setTextViewColor(baseContext, tvMayIAsk, R.color.colorSmokeWhite)
        helper.setTextViewColor(baseContext, btnContinuePhone, R.color.colorPrimary)
    }

    private fun continueClickListener() {

        btnContinuePhone.setOnClickListener {
            attemptToSendVerificationCode()
        }
    }

    private fun verificationCallbacks() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                println("it works")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                println("It failed")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                println("code sent")
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
            }

        }
    }

    private fun attemptToSendVerificationCode() {

        verificationCallbacks()

        val phone = etPhoneNumber.text.toString()

        if(phone != "") {
            println("Get here")
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
            )
        }
    }

    private fun startupSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)

        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if(item.itemId == android.R.id.home) {
            startupSignUpActivity()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        startupSignUpActivity()
        super.onBackPressed()
    }

}