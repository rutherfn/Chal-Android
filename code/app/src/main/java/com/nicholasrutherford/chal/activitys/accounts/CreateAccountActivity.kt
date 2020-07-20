package com.nicholasrutherford.chal.activitys.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.fragments.ErrorCreateAccount
import com.nicholasrutherford.chal.fragments.LoadingDialog
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import de.hdodenhof.circleimageview.CircleImageView


class CreateAccountActivity  : AppCompatActivity() {

    // declarations
    private lateinit var tbCreateAccount: Toolbar
    private lateinit var tvCreate: TextView
    private lateinit var tvAccount: TextView
    private lateinit var cvChalLogo: CircleImageView

    private lateinit var tvName: TextView
    private lateinit var etName: EditText
    private lateinit var ivName: ImageView
    private lateinit var ivErrorName: ImageView
    private lateinit var tvErrorName: TextView

    private lateinit var tvUser: TextView
    private lateinit var etUsername: EditText
    private lateinit var ivUsername: ImageView
    private lateinit var ivErrorUsername: ImageView
    private lateinit var tvErrorUsername: TextView

    private lateinit var tvEmail: TextView
    private lateinit var etEmail: EditText
    private lateinit var ivEmail: ImageView
    private lateinit var ivErrorEmail: ImageView
    private lateinit var tvErrorEmail: TextView

    private lateinit var tvPhone: TextView
    private lateinit var etPhone: EditText
    private lateinit var ivPhone: ImageView
    private lateinit var ivErrorPhone: ImageView
    private lateinit var tvErrorPhone: TextView

    private lateinit var tvPassword: TextView
    private lateinit var etPassword: EditText
    private lateinit var ivPassword: ImageView
    private lateinit var ivErrorPassword: ImageView
    private lateinit var tvErrorPassword: TextView

    private lateinit var btContinueCreating: Button

    private var etNameValue = ""
    private var etUsernameValue = ""
    private var etEmailValue = ""
    private var etPhoneValue = ""
    private var etPasswordValue = ""
    private var isEmptyEmail = false

    private var loadingDialog = LoadingDialog()
    private var errorCreateAccount = ErrorCreateAccount()
    private val fm = supportFragmentManager

    private val typeface = Typeface()
    private val helper = Helper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        main()
    }


    private fun main() {
        setupView()
        editTextFieldListeners()
        listenersForCloseKeyboard()
        createAccountListener()
    }

    private fun setupView() {
        setUpIds()
        hideAllErrorsOnStartup()
        setupToolbar()
        setupTypeface()
        setupTextViewsColor()
    }

    private fun setUpIds() {

        tbCreateAccount = findViewById(R.id.tbCreateAccount)
        tvCreate = findViewById(R.id.tvCreate)
        tvAccount = findViewById(R.id.tvAccount)

        cvChalLogo = findViewById(R.id.cvChalLogo)

        tvName = findViewById(R.id.tvName)
        etName = findViewById(R.id.etName)
        ivName = findViewById(R.id.ivName)
        ivErrorName = findViewById(R.id.ivErrorName)
        tvErrorName = findViewById(R.id.tvErrorName)

        tvUser = findViewById(R.id.tvUser)
        etUsername = findViewById(R.id.etUsername)
        ivUsername = findViewById(R.id.ivUsername)
        ivErrorUsername = findViewById(R.id.ivErrorUsername)
        tvErrorUsername = findViewById(R.id.tvErrorUsername)

        tvEmail = findViewById(R.id.tvEmail)
        etEmail = findViewById(R.id.etEmail)
        ivEmail = findViewById(R.id.ivEmail)
        tvErrorEmail = findViewById(R.id.tvErrorEmail)
        ivErrorEmail = findViewById(R.id.ivErrorEmail)

        tvPhone = findViewById(R.id.tvPhone)
        etPhone = findViewById(R.id.etPhone)
        ivPhone = findViewById(R.id.ivPhone)
        ivErrorPhone = findViewById(R.id.ivErrorPhone)
        tvErrorPhone = findViewById(R.id.tvErrorPhone)

        tvPassword = findViewById(R.id.tvPassword)
        etPassword = findViewById(R.id.etPassword)
        ivPassword = findViewById(R.id.ivPassword)
        ivErrorPassword = findViewById(R.id.ivErrorPassword)
        tvErrorPassword = findViewById(R.id.tvErrorPassword)

        btContinueCreating = findViewById(R.id.btContinueCreating)

    }

    private fun hideAllErrorsOnStartup() {

        hideErrorsName()

        hideErrorsUsername()

        hideErrorEmails()

        hideErrorsPhone()

        hideErrorPassword()
    }

    private fun hideErrorsName() {
        ivErrorName.visibility = View.GONE
        tvErrorName.visibility = View.GONE
    }

    private fun showErrorsName() {
        ivErrorName.visibility = View.VISIBLE
        tvErrorName.visibility = View.VISIBLE
    }

    private fun hideErrorsUsername() {
        ivErrorUsername.visibility = View.GONE
        tvErrorUsername.visibility = View.GONE
    }

    private fun showErrorsUsername() {
        ivErrorUsername.visibility = View.VISIBLE
        tvErrorUsername.visibility = View.VISIBLE
    }

    private fun showErrorsEmailEmpty() {
        ivErrorEmail.visibility = View.VISIBLE
        tvErrorEmail.visibility = View.VISIBLE

        tvErrorEmail.text = getString(R.string.error_email_entered)
    }

    private fun showErrorsEmailNotValid() {
        ivErrorEmail.visibility = View.VISIBLE
        tvErrorEmail.visibility = View.VISIBLE

        tvErrorEmail.text = getString(R.string.error_email)
    }

    private fun hideErrorEmails() {
        ivErrorEmail.visibility = View.GONE
        tvErrorEmail.visibility = View.GONE
    }

    private fun showErrorsPhoneNotCorrectFormat() {
        ivErrorPhone.visibility = View.VISIBLE
        tvErrorPhone.visibility = View.VISIBLE

        tvErrorPhone.text = getText(R.string.error_phone_not_valid)
    }

    private fun hideErrorsPhone() {
        ivErrorPhone.visibility = View.GONE
        tvErrorPhone.visibility = View.GONE
    }

    private fun showErrorPassword() {
        ivErrorPassword.visibility = View.VISIBLE
        tvErrorPassword.visibility = View.VISIBLE

        tvErrorPassword.text = getText(R.string.error_password)
    }

    private fun hideErrorPassword() {
        ivErrorPassword.visibility = View.GONE
        tvErrorPassword.visibility = View.GONE
    }

    private fun setupToolbar() {
        setSupportActionBar(tbCreateAccount)

        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTypeface() {
        typeface.setTypefaceForHeaderBold(tvCreate, baseContext)
        typeface.setTypefaceForHeaderBold(tvAccount, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvName, baseContext)
        typeface.setTypefaceForBodyBold(tvErrorName, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvUser, baseContext)
        typeface.setTypefaceForBodyBold(tvErrorUsername, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvEmail, baseContext)
        typeface.setTypefaceForBodyBold(tvErrorEmail, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvPhone, baseContext)
        typeface.setTypefaceForBodyBold(tvErrorPhone, baseContext)

        typeface.setTypefaceForSubHeaderBold(tvPassword, baseContext)
        typeface.setTypefaceForBodyBold(tvErrorPassword, baseContext)

        typeface.setTypefaceForSubHeaderBold(btContinueCreating, baseContext)
    }

    private fun setupTextViewsColor() {
        helper.setTextViewColor(baseContext, tvCreate, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvAccount, R.color.colorPrimary)

        helper.setTextViewColor(baseContext,tvName, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvErrorName, R.color.colorBlack)

        helper.setTextViewColor(baseContext,tvUser, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvErrorUsername, R.color.colorBlack)

        helper.setTextViewColor(baseContext,tvEmail, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvErrorEmail, R.color.colorBlack)

        helper.setTextViewColor(baseContext,tvPhone, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvErrorPhone, R.color.colorBlack)

        helper.setTextViewColor(baseContext,tvPassword, R.color.colorPrimary)
        helper.setTextViewColor(baseContext, tvErrorPassword, R.color.colorBlack)

        helper.setTextViewColor(baseContext, btContinueCreating, R.color.colorBlack)

    }

    private fun setEditTextValues() {
        etNameValue = etName.text.toString()
        etUsernameValue = etUsername.text.toString()
        etEmailValue = etEmail.text.toString()
        etPhoneValue = etPhone.text.toString()
        etPasswordValue = etPassword.text.toString()
    }

    private fun listenersForCloseKeyboard() {
        closeKeyboardIfUserHitsDone(etName)
        closeKeyboardIfUserHitsDone(etUsername)
        closeKeyboardIfUserHitsDone(etEmail)
        closeKeyboardIfUserHitsDone(etPhone)
        closeKeyboardIfUserHitsDone(etPassword)
    }

    private fun editTextFieldListeners() {

        etName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfNameIsCorrect() }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etUsername.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfUsernameIsCorrect() }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etEmail.addTextChangedListener(object: TextWatcher{

            override fun afterTextChanged(s: Editable?) { checkIfEmailIsCorrect() }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etPhone.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfPhoneIsCorrect() }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) { checkIfPasswordIsCorrect() }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun checkIfNameIsCorrect() {
        if(!isNameCorrectFormat()) {
            showErrorsName()
        } else {
            hideErrorsName()
        }
    }

    private fun isNameCorrectFormat(): Boolean {
        setEditTextValues()

        return etNameValue != ""
    }

    private fun checkIfUsernameIsCorrect() {
        if(!isUsernameCorrectFormat()) {
            showErrorsUsername()
        } else {
            hideErrorsUsername()
        }
    }

    private fun isUsernameCorrectFormat(): Boolean {
        setEditTextValues()

        return etUsernameValue != ""
    }

    private fun checkIfEmailIsCorrect() {

        if(!isEmailCorrectFormat() && isEmptyEmail) {
            showErrorsEmailEmpty()
        } else if(!isEmailCorrectFormat() && !isEmptyEmail) {
            showErrorsEmailNotValid()
        } else if(isEmailCorrectFormat() && !isEmptyEmail) {
            hideErrorEmails()
        }
    }

    private fun isEmailCorrectFormat() : Boolean {
        setEditTextValues()

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

    private fun checkIfPhoneIsCorrect() {
        if(!isPhoneCorrectFormat()) {
            showErrorsPhoneNotCorrectFormat()
        } else {
            hideErrorsPhone()
        }
    }

    private fun isPhoneCorrectFormat(): Boolean {

        setEditTextValues()

        return !etPhoneValue.contains("-")
    }

    private fun checkIfPasswordIsCorrect() {
        if(!isPasswordCorrectFormat()) {
            showErrorPassword()
        } else {
            hideErrorPassword()
        }
    }

    private fun isPasswordCorrectFormat() : Boolean {

        setEditTextValues()

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

    private fun isAllFieldsEnteredCorrectly(): Boolean {
        return isEmailCorrectFormat() && isNameCorrectFormat() && isUsernameCorrectFormat() && isPhoneCorrectFormat() && isPasswordCorrectFormat()
    }

    private fun clearAllEditTexts() {
        etName.text.clear()
        etUsername.text.clear()
        etEmail.text.clear()
        etPhone.text.clear()
        etPassword.text.clear()
    }

    private fun attemptToCreateAccount() {

        if(this.isAllFieldsEnteredCorrectly()) {
            loadingDialog.show(fm, "LoadingDialog")
            loadingDialog.dismiss()
            startUploadPhotoActivity()
        } else {

            checkIfNameIsCorrect()
            checkIfUsernameIsCorrect()

            checkIfEmailIsCorrect()
            checkIfPhoneIsCorrect()
            checkIfPasswordIsCorrect()

            errorCreateAccount.show(fm, "ErrorCreateAccountDialog")
        }
    }

    private fun saveUserIntentForNextScreen(): Intent {

        setEditTextValues()

        val intent = Intent(this@CreateAccountActivity, UploadPhotoActivity::class.java)

        intent.putExtra("fullName", etNameValue)
        intent.putExtra("username", etUsernameValue)
        intent.putExtra("phone", etPhoneValue)
        intent.putExtra("password", etPasswordValue)

       // clearAllEditTexts()

        return intent
    }

    private fun startUploadPhotoActivity() {
        startActivity(saveUserIntentForNextScreen())
    }

    private fun createAccountListener() {

        btContinueCreating.setOnClickListener {
            attemptToCreateAccount()
        }
    }

}