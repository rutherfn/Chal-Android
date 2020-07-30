package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityLoginBinding
import com.nicholasrutherford.chal.fragments.dialogs.ErrorCreateAccountDialog
import com.nicholasrutherford.chal.fragments.dialogs.ErrorLoginToAccount
import com.nicholasrutherford.chal.fragments.dialogs.LoadingDialog
import com.nicholasrutherford.chal.fragments.dialogs.SuccessCreateAccountDialog
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class LoginActivity : AppCompatActivity() {

    // declarations
    private var typeface = Typeface()
    private var helper = Helper()

    private var errorLoginToAccountDialog = ErrorLoginToAccount()
    private var errorLogInAccountDueToFieldsDialog = ErrorCreateAccountDialog()
    private var loadingDialog = LoadingDialog()
    private var loadingAccountDialog = SuccessCreateAccountDialog()
    private val fm = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        main(binding)
    }

    private fun main(binding: ActivityLoginBinding) {
        setUpView(binding)
        etEditorActionListeners(binding)
        checkIfEmailIsEnteredCorrectly(binding)
        listeners(binding)
    }

    private fun setUpView(binding: ActivityLoginBinding) {
        setUpTypeface(binding)
        setUpTextViewColors(binding)
    }

    private fun setUpTypeface(binding: ActivityLoginBinding) {
        typeface.setTypefaceForHeaderBold(binding.tvTitle, baseContext)
        typeface.setTypefaceForHeaderRegular(binding.tvSubTitle, baseContext)

        typeface.setTypefaceForBodyBold(binding.tvEmail, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvPassword, baseContext)

        typeface.setTypefaceForHeaderBold(binding.btLogIn, baseContext)

        typeface.setTypefaceForBodyItalic(binding.tvForgotPassword, baseContext)
        typeface.setTypefaceForBodyLight(binding.tvDoNotHaveAccount, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvSignUp, baseContext)
        typeface.setTypefaceForSubHeaderBold(binding.tvErrorEmail, baseContext)
    }

    private fun setUpTextViewColors(binding: ActivityLoginBinding) { // if its dark mode setup normal colors else another one when we get to it
        helper.setTextViewColor(baseContext, binding.tvTitle, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvSubTitle, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvEmail, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvPassword, R.color.colorPrimary)

        helper.setTextViewColor(baseContext, binding.btLogIn, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvForgotPassword, R.color.colorBlue)

        helper.setTextViewColor(baseContext, binding.tvDoNotHaveAccount, R.color.colorBlack)
        helper.setTextViewColor(baseContext, binding.tvSignUp, R.color.colorBlue)

        helper.setTextViewColor(baseContext, binding.tvErrorEmail, R.color.colorBlack)
    }

    private fun listeners(binding: ActivityLoginBinding) {
        binding.btLogIn.setOnClickListener {
            attemptToSignUserIntoFirebase(binding)
        }

        binding.tvSignUp.setOnClickListener {
            startSignUpActivity()
        }

        binding.tvForgotPassword.setOnClickListener {
            startForgotPasswordActivity()
        }

    }

    private fun etEditorActionListeners(binding: ActivityLoginBinding) {
        binding.etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }

        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            val email = binding.etEmail.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
                attemptToSignUserIntoFirebase(binding)
            } else if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }

    }

    private fun showErrorEmail(binding: ActivityLoginBinding) {
        binding.tvErrorEmail.visibility = View.VISIBLE
        binding.ivErrorEmail.visibility = View.VISIBLE
    }

    private fun dismissErrorEmail(binding: ActivityLoginBinding) {
        binding.tvErrorEmail.visibility = View.GONE
        binding.ivErrorEmail.visibility = View.GONE
    }

    private fun isEmailError(binding: ActivityLoginBinding): Boolean {
        return binding.ivErrorEmail.visibility == View.VISIBLE && binding.tvErrorEmail.visibility == View.VISIBLE
    }

    private fun checkIfEmailIsEnteredCorrectly(binding: ActivityLoginBinding) {
        binding.etEmail.addTextChangedListener (object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                val email = binding.etEmail.text.toString()

                if(email.contains("@") && email.contains(".com")) {
                    dismissErrorEmail(binding)
                } else if(email == "") {
                    dismissErrorEmail(binding)
                } else {
                    showErrorEmail(binding)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        }

    private fun attemptToSignUserIntoFirebase(binding: ActivityLoginBinding) {

        helper.hideSoftKeyBoard(this)

        if(isEmailError(binding)) {
            errorLogInAccountDueToFieldsDialog.show(fm, "ErrorLogInAccountDueToFieldsDialog")
        } else {

            loadingDialog.show(fm, "LoadingDialog")

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    loadingDialog.dismiss()

                    loadingAccountDialog.show(fm, "LoadingAccountDialog")
                }
                .addOnFailureListener {

                    loadingDialog.dismiss()

                    errorLoginToAccountDialog.show(fm, "ErrorLoginToAccountDialog")

                    binding.etEmail.text.clear()
                    binding.etPassword.text.clear()
                }

        }

        // logic follows for firebase events
    }

    private fun clearEditTextFields(binding: ActivityLoginBinding) {
        binding.etEmail.text.clear()
        binding.etPassword.text.clear()
    }

    private fun startSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun startForgotPasswordActivity() {
        val intent = Intent(applicationContext, ForgotPasswordActivity::class.java)

        startActivity(intent)
        finish()
    }

}