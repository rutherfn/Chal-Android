package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityCreateAccountBinding
import com.nicholasrutherford.chal.dialogfragments.ErrorCreateAccountDialogFragment
import com.nicholasrutherford.chal.dialogfragments.LoadingDialogFragement
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class CreateAccountActivity  : AppCompatActivity() {

    // declarations
    private var etNameValue = ""
    private var etUsernameValue = ""
    private var etEmailValue = ""
    private var etPhoneValue = ""
    private var etPasswordValue = ""
    private var isEmptyEmail = false

    private var loadingDialog =
        LoadingDialogFragement()
    private var errorCreateAccount =
        ErrorCreateAccountDialogFragment()
    private val fm = supportFragmentManager

    private val typeface = Typeface()
    private val helper = Helper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        main(binding)
    }


    private fun main(binding: ActivityCreateAccountBinding) {
        setupView(binding)
        editTextFieldListeners(binding)
        listenersForCloseKeyboard(binding)
        createAccountListener(binding)
    }

    private fun setupView(binding: ActivityCreateAccountBinding) {
        hideAllErrorsOnStartup(binding)
        setupToolbar(binding)
        setupTypeface(binding)
        setupTextViewsColor(binding)
    }

    private fun hideAllErrorsOnStartup(binding: ActivityCreateAccountBinding) {

        hideErrorsName(binding)

        hideErrorsUsername(binding)

        hideErrorEmails(binding)

        hideErrorsPhone(binding)

        hideErrorPassword(binding)
    }

    private fun hideErrorsName(binding: ActivityCreateAccountBinding) {
        binding.ivErrorName.visibility = View.GONE
        binding.tvErrorName.visibility = View.GONE
    }

    private fun showErrorsName(binding: ActivityCreateAccountBinding) {
        binding.ivErrorName.visibility = View.VISIBLE
        binding.tvErrorName.visibility = View.VISIBLE
    }

    private fun hideErrorsUsername(binding: ActivityCreateAccountBinding) {
        binding.ivErrorUsername.visibility = View.GONE
        binding.tvErrorUsername.visibility = View.GONE
    }

    private fun showErrorsUsername(binding: ActivityCreateAccountBinding) {
        binding.ivErrorUsername.visibility = View.VISIBLE
        binding.tvErrorUsername.visibility = View.VISIBLE
    }

    private fun showErrorsEmailEmpty(binding: ActivityCreateAccountBinding) {
        binding.ivErrorEmail.visibility = View.VISIBLE
        binding.tvErrorEmail.visibility = View.VISIBLE

        binding.tvErrorEmail.text = getString(R.string.error_email_entered)
    }

    private fun showErrorsEmailNotValid(binding: ActivityCreateAccountBinding) {
        binding.ivErrorEmail.visibility = View.VISIBLE
        binding.tvErrorEmail.visibility = View.VISIBLE

        binding.tvErrorEmail.text = getString(R.string.error_email)
    }

    private fun hideErrorEmails(binding: ActivityCreateAccountBinding) {
        binding.ivErrorEmail.visibility = View.GONE
        binding.tvErrorEmail.visibility = View.GONE
    }

    private fun showErrorsPhoneNotCorrectFormat(binding: ActivityCreateAccountBinding) {
        binding.ivErrorPhone.visibility = View.VISIBLE
        binding.tvErrorPhone.visibility = View.VISIBLE

        binding.tvErrorPhone.text = getText(R.string.error_phone_not_valid)
    }

    private fun hideErrorsPhone(binding: ActivityCreateAccountBinding) {
        binding.ivErrorPhone.visibility = View.GONE
        binding.tvErrorPhone.visibility = View.GONE
    }

    private fun showErrorPassword(binding: ActivityCreateAccountBinding) {
        binding.ivErrorPassword.visibility = View.VISIBLE
        binding.tvErrorPassword.visibility = View.VISIBLE

        binding.tvErrorPassword.text = getText(R.string.error_password)
    }

    private fun hideErrorPassword(binding: ActivityCreateAccountBinding) {
        binding.ivErrorPassword.visibility = View.GONE
        binding.tvErrorPassword.visibility = View.GONE
    }

    private fun setupToolbar(binding: ActivityCreateAccountBinding) {
        setSupportActionBar(binding.tbCreateAccount)

        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTypeface(binding: ActivityCreateAccountBinding) {
        typeface.setTypefaceForHeaderBold(binding.tvCreate, baseContext)
        typeface.setTypefaceForHeaderBold(binding.tvAccount, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.tvName, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvErrorName, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.tvUser, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvErrorUsername, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.tvEmail, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvErrorEmail, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.tvPhone, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvErrorPhone, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.tvPassword, baseContext)
        typeface.setTypefaceForBodyBold(binding.tvErrorPassword, baseContext)

        typeface.setTypefaceForSubHeaderBold(binding.btContinueCreating, baseContext)
    }

    private fun setupTextViewsColor(binding: ActivityCreateAccountBinding) {
        helper.setTextViewColor(baseContext, binding.tvCreate, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvAccount, R.color.colorPrimary)

        helper.setTextViewColor(baseContext, binding.tvName, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvErrorName, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvUser, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvErrorUsername, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvEmail, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvErrorEmail, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvPhone, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvErrorPhone, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.tvPassword, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, binding.tvErrorPassword, R.color.colorBlack)

        helper.setTextViewColor(baseContext, binding.btContinueCreating, R.color.colorBlack)

    }

    private fun setEditTextValues(binding: ActivityCreateAccountBinding) {
        etNameValue = binding.etName.text.toString()
        etUsernameValue = binding.etUsername.text.toString()
        etEmailValue = binding.etEmail.text.toString()
        etPhoneValue = binding.etPhone.text.toString()
        etPasswordValue = binding.etPassword.text.toString()
    }

    private fun listenersForCloseKeyboard(binding: ActivityCreateAccountBinding) {
        closeKeyboardIfUserHitsDone(binding.etName)
        closeKeyboardIfUserHitsDone(binding.etUsername)
        closeKeyboardIfUserHitsDone(binding.etEmail)
        closeKeyboardIfUserHitsDone(binding.etPhone)
        closeKeyboardIfUserHitsDone(binding.etPassword)
    }

    private fun editTextFieldListeners(binding: ActivityCreateAccountBinding) {

        binding.etName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfNameIsCorrect(binding) }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        binding.etUsername.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfUsernameIsCorrect(binding) }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        binding.etEmail.addTextChangedListener(object: TextWatcher{

            override fun afterTextChanged(s: Editable?) { checkIfEmailIsCorrect(binding) }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        binding.etPhone.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfPhoneIsCorrect(binding) }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        binding.etPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfPasswordIsCorrect(binding) }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun checkIfNameIsCorrect(binding: ActivityCreateAccountBinding) {
        if(!isNameCorrectFormat(binding)) {
            showErrorsName(binding)
        } else {
            hideErrorsName(binding)
        }
    }

    private fun isNameCorrectFormat(binding: ActivityCreateAccountBinding): Boolean {
        setEditTextValues(binding)

        return etNameValue != ""
    }

    private fun checkIfUsernameIsCorrect(binding: ActivityCreateAccountBinding) {
        if(!isUsernameCorrectFormat(binding)) {
            showErrorsUsername(binding)
        } else {
            hideErrorsUsername(binding)
        }
    }

    private fun isUsernameCorrectFormat(binding: ActivityCreateAccountBinding): Boolean {
        setEditTextValues(binding)

        return etUsernameValue != ""
    }

    private fun checkIfEmailIsCorrect(binding: ActivityCreateAccountBinding) {

        if(!isEmailCorrectFormat(binding) && isEmptyEmail) {
            showErrorsEmailEmpty(binding)
        } else if(!isEmailCorrectFormat(binding) && !isEmptyEmail) {
            showErrorsEmailNotValid(binding)
        } else if(isEmailCorrectFormat(binding) && !isEmptyEmail) {
            hideErrorEmails(binding)
        }
    }

    private fun isEmailCorrectFormat(binding: ActivityCreateAccountBinding) : Boolean {
        setEditTextValues(binding)

        return if(etEmailValue.contains("@") && etEmailValue.contains(".com")) {
            isEmptyEmail = false
            true
        } else if(etEmailValue == "") {
            isEmptyEmail = true
            false
        } else {
            isEmptyEmail = false
            false
        }
    }

    private fun checkIfPhoneIsCorrect(binding: ActivityCreateAccountBinding) {
        if(!isPhoneCorrectFormat(binding)) {
            showErrorsPhoneNotCorrectFormat(binding)
        } else {
            hideErrorsPhone(binding)
        }
    }

    private fun isPhoneCorrectFormat(binding: ActivityCreateAccountBinding): Boolean {

        setEditTextValues(binding)

        return !etPhoneValue.contains("-")
    }

    private fun checkIfPasswordIsCorrect(binding: ActivityCreateAccountBinding) {
        if(!isPasswordCorrectFormat(binding)) {
            showErrorPassword(binding)
        } else {
            hideErrorPassword(binding)
        }
    }

    private fun isPasswordCorrectFormat(binding: ActivityCreateAccountBinding) : Boolean {

        setEditTextValues(binding)

        val hasUpperCase = etPasswordValue != etPasswordValue.toLowerCase()

        return !(!hasUpperCase && !etPasswordValue.contains(".*\\d.*"))
    }

    private fun closeKeyboardIfUserHitsDone(editText: EditText) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(this)
            }
            false
        }
    }

    private fun isAllFieldsEnteredCorrectly(binding: ActivityCreateAccountBinding): Boolean {
        return isEmailCorrectFormat(binding) && isNameCorrectFormat(binding) && isUsernameCorrectFormat(binding)
                && isPhoneCorrectFormat(binding) && isPasswordCorrectFormat(binding)
    }

    private fun clearAllEditTexts(binding: ActivityCreateAccountBinding) {
        binding.etName.text.clear()
        binding.etUsername.text.clear()
        binding.etEmail.text.clear()
        binding.etPhone.text.clear()
        binding.etPassword.text.clear()
    }

    private fun attemptToCreateAccount(binding: ActivityCreateAccountBinding) {

        if(this.isAllFieldsEnteredCorrectly(binding)) {
            loadingDialog.show(fm, "LoadingDialog")
            loadingDialog.dismiss()
            startUploadPhotoActivity(binding)
        } else {

            checkIfNameIsCorrect(binding)
            checkIfUsernameIsCorrect(binding)

            checkIfEmailIsCorrect(binding)
            checkIfPhoneIsCorrect(binding)
            checkIfPasswordIsCorrect(binding)

            errorCreateAccount.show(fm, "ErrorCreateAccountDialog")
        }
    }

    private fun saveUserIntentForNextScreen(binding: ActivityCreateAccountBinding): Intent {

        setEditTextValues(binding)

        val intent = Intent(this@CreateAccountActivity, UploadPhotoActivity::class.java)

        intent.putExtra("fullName", etNameValue)
        intent.putExtra("username", etUsernameValue)
        intent.putExtra("email", etEmailValue)
        intent.putExtra("phone", etPhoneValue)
        intent.putExtra("password", etPasswordValue)

       // clearAllEditTexts()

        return intent
    }

    private fun startUploadPhotoActivity(binding: ActivityCreateAccountBinding) {
        startActivity(saveUserIntentForNextScreen(binding))
    }

    private fun createAccountListener(binding: ActivityCreateAccountBinding) {

        binding.btContinueCreating.setOnClickListener {
            attemptToCreateAccount(binding)
        }
    }

    private fun startSignUpActivity() {
        val intent = Intent(applicationContext, SignUpActivity::class.java)

        startActivity(intent)
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if(item.itemId == android.R.id.home) {
            startSignUpActivity()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        startSignUpActivity()
        super.onBackPressed()
    }

}