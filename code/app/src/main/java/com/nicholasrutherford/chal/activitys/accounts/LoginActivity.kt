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
import com.nicholasrutherford.chal.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    // declarations
    private var typeface = Typeface()
    private var helper = Helper()

    private var viewModel: LoginViewModel? = null

    private var errorLoginToAccountDialog = ErrorLoginToAccount()
    private var errorLogInAccountDueToFieldsDialog = ErrorCreateAccountDialog()
    private var loadingDialog = LoadingDialog()
    private var loadingAccountDialog = SuccessCreateAccountDialog()
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel(applicationContext)
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

    private fun setUpTypeface(binding: ActivityLoginBinding) { // ui stuff here
        typeface.setHeaderTypefaceBold(binding.tvTitle, baseContext, viewModel?.viewState?.configurationEntity?.primaryHeaderTypefaceBold!!)

        typeface.setTypefaceForBoldBody(binding.tvEmail, baseContext, viewModel?.viewState?.configurationEntity?.bodyTypefaceBold!!)
        typeface.setTypefaceForBoldBody(binding.tvPassword, baseContext, viewModel?.viewState?.configurationEntity?.bodyTypefaceBold!!)

        typeface.setHeaderTypefaceBold(binding.btLogIn, baseContext, viewModel?.viewState?.configurationEntity?.primaryHeaderTypefaceBold!!)

        typeface.setTypefaceForItalicBody(binding.tvForgotPassword, baseContext, viewModel?.viewState?.configurationEntity?.bodyTypefaceItalic!!)
        typeface.setTypefaceForLightBody(binding.tvDoNotHaveAccount, baseContext, viewModel?.viewState?.configurationEntity?.bodyTypefaceItalic!!)
        typeface.setTypefaceForBoldBody(binding.tvSignUp, baseContext, viewModel?.viewState?.configurationEntity?.bodyTypefaceBold!!)
        typeface.setTypefaceForBoldSubHeader(binding.tvErrorEmail, baseContext, viewModel?.viewState?.configurationEntity?.subHeaderTypefaceBold!!)
    }

    private fun setUpTextViewColors(binding: ActivityLoginBinding) { // if its dark mode setup normal colors else another one when we get to it
        val primaryColor = viewModel?.viewState?.configurationEntity?.primaryColor!!
        val secondaryColor = viewModel?.viewState?.configurationEntity?.secondaryColor!!

        helper.setTextViewColorWithString(baseContext, binding.tvTitle, primaryColor)
        helper.setTextViewColorWithString(baseContext, binding.tvSubTitle, secondaryColor)

        helper.setTextViewColorWithString(baseContext, binding.tvEmail, primaryColor)
        helper.setTextViewColorWithString(baseContext, binding.tvPassword, primaryColor)

        helper.setTextViewColor(baseContext, binding.btLogIn, R.color.colorSmokeWhite)

        helper.setTextViewColor(baseContext, binding.tvForgotPassword, R.color.colorBlue)

        helper.setTextViewColorWithString(baseContext, binding.tvDoNotHaveAccount, secondaryColor)
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

        viewModel?.isEmailErrorVisible()
    }

    private fun dismissErrorEmail(binding: ActivityLoginBinding) {
        binding.tvErrorEmail.visibility = View.GONE
        binding.ivErrorEmail.visibility = View.GONE

        viewModel?.isEmailErrorNotVisible()
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