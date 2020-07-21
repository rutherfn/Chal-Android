package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.fragments.dialogs.ErrorCreateAccountDialog
import com.nicholasrutherford.chal.fragments.dialogs.ErrorLoginToAccount
import com.nicholasrutherford.chal.fragments.dialogs.LoadingDialog
import com.nicholasrutherford.chal.fragments.dialogs.SuccessCreateAccountDialog
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class LoginActivity : AppCompatActivity() {

    // declarations
    private lateinit var ivSignInPrimaryLogo: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var tvEmail: TextView
    private lateinit var etEmail: EditText
    private lateinit var ivEmail: ImageView
    private lateinit var tvPassword: TextView
    private lateinit var etPassword: EditText
    private lateinit var ivPassword: ImageView
    private lateinit var btLogIn: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvDoNotHaveAccount: TextView
    private lateinit var tvSignUp: TextView
    private lateinit var ivErrorEmail: ImageView
    private lateinit var tvErrorEmail: TextView

    private var typeface = Typeface()
    private var helper = Helper()

    private var errorLoginToAccountDialog = ErrorLoginToAccount()
    private var errorLogInAccountDueToFieldsDialog = ErrorCreateAccountDialog()
    private var loadingDialog = LoadingDialog()
    private var loadingAccountDialog = SuccessCreateAccountDialog()
    private val fm = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        main()
    }

    private fun main() {
        setUpView()
        etEditorActionListeners()
        checkIfEmailIsEnteredCorrectly()
        listeners()
    }

    private fun setUpView() {
        setUpIds()
        setUpTypeface()
        setUpTextViewColors()
    }

    private fun setUpIds() {

        ivSignInPrimaryLogo = findViewById(R.id.ivSignInPrimaryLogo)
        tvTitle = findViewById(R.id.tvTitle)
        tvSubTitle = findViewById(R.id.tvSubTitle)
        tvEmail = findViewById(R.id.tvEmail)

        etEmail = findViewById(R.id.etEmail)
        ivEmail = findViewById(R.id.ivEmail)
        tvPassword = findViewById(R.id.tvPassword)
        etPassword = findViewById(R.id.etPassword)
        ivPassword = findViewById(R.id.ivPassword)
        btLogIn = findViewById(R.id.btLogIn)

        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvDoNotHaveAccount = findViewById(R.id.tvDoNotHaveAccount)
        tvSignUp = findViewById(R.id.tvSignUp)

        ivErrorEmail = findViewById(R.id.ivErrorEmail)
        tvErrorEmail = findViewById(R.id.tvErrorEmail)
    }

    private fun setUpTypeface() {
        typeface.setTypefaceForHeaderBold(tvTitle, baseContext)
        typeface.setTypefaceForHeaderRegular(tvSubTitle, baseContext)

        typeface.setTypefaceForBodyBold(tvEmail, baseContext)
        typeface.setTypefaceForBodyBold(tvPassword, baseContext)

        typeface.setTypefaceForHeaderBold(btLogIn, baseContext)

        typeface.setTypefaceForBodyItalic(tvForgotPassword, baseContext)
        typeface.setTypefaceForBodyLight(tvDoNotHaveAccount, baseContext)
        typeface.setTypefaceForBodyBold(tvSignUp, baseContext)
        typeface.setTypefaceForSubHeaderBold(tvErrorEmail, baseContext)
    }

    private fun setUpTextViewColors() { // if its dark mode setup normal colors else another one when we get to it
        helper.setTextViewColor(baseContext, tvTitle, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvSubTitle, R.color.colorBlack)

        helper.setTextViewColor(baseContext, tvEmail, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvPassword, R.color.colorPrimary)

        helper.setTextViewColor(baseContext, btLogIn, R.color.colorBlack)

        helper.setTextViewColor(baseContext, tvForgotPassword, R.color.colorBlue)

        helper.setTextViewColor(baseContext, tvDoNotHaveAccount, R.color.colorBlack)
        helper.setTextViewColor(baseContext, tvSignUp, R.color.colorBlue)

        helper.setTextViewColor(baseContext, tvErrorEmail, R.color.colorBlack)
    }

    private fun listeners() {
        btLogIn.setOnClickListener {
            attemptToSignUserIntoFirebase()
        }

        tvSignUp.setOnClickListener {
            startSignUpActivity()
        }

        tvForgotPassword.setOnClickListener {
            startForgotPasswordActivity()
        }

    }

    private fun etEditorActionListeners() {
        etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }

        etPassword.setOnEditorActionListener { _, actionId, _ ->
            val email = etEmail.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
                attemptToSignUserIntoFirebase()
            } else if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }

    }

    private fun showErrorEmail() {
        tvErrorEmail.visibility = View.VISIBLE
        ivErrorEmail.visibility = View.VISIBLE
    }

    private fun dismissErrorEmail() {
        tvErrorEmail.visibility = View.GONE
        ivErrorEmail.visibility = View.GONE
    }

    private fun isEmailError(): Boolean {
        return ivErrorEmail.visibility == View.VISIBLE && tvErrorEmail.visibility == View.VISIBLE
    }

    private fun checkIfEmailIsEnteredCorrectly() {
        etEmail.addTextChangedListener (object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                val email = etEmail.text.toString()

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

    private fun attemptToSignUserIntoFirebase() {

        helper.hideSoftKeyBoard(this)

        if(isEmailError()) {
            errorLogInAccountDueToFieldsDialog.show(fm, "ErrorLogInAccountDueToFieldsDialog")
        } else {

            loadingDialog.show(fm, "LoadingDialog")

            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    loadingDialog.dismiss()

                    loadingAccountDialog.show(fm, "LoadingAccountDialog")
                }
                .addOnFailureListener {

                    loadingDialog.dismiss()

                    errorLoginToAccountDialog.show(fm, "ErrorLoginToAccountDialog")

                    etEmail.text.clear()
                    etPassword.text.clear()
                }

        }

        println("Attempt to sign in to firebase user account")

        // logic follows for firebase events
    }

    private fun startupMainActivity() {
        clearEditTextFields()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun clearEditTextFields() {
        etEmail.text.clear()
        etPassword.text.clear()
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