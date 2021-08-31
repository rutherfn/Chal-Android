package com.nicholasrutherford.chal.account.validation

import android.app.Application
import javax.inject.Inject

@Suppress("MagicNumber")
const val AT_SYMBOL = "@"

class AccountValidationImpl @Inject constructor(private val application: Application) : AccountValidation {

    override fun isEmailEmpty(email: String): Boolean {
        val empty = application.getString(R.string.empty_string)
        return email == empty
    }

    override fun isEmailMeetRequirements(email: String): Boolean {
        val dotCom = application.getString(R.string.dot_com)
        return email.contains(AT_SYMBOL) && email.contains(dotCom)
    }

    override fun isEmailValid(email: String): Boolean {
        return !isEmailEmpty(email) && isEmailMeetRequirements(email)
    }

    override fun isPasswordEmpty(password: String): Boolean {
        val empty = application.getString(R.string.empty_string)
        return password == empty
    }

    override fun isPasswordOrEmailEmpty(email: String, password: String) : Boolean {
        return isPasswordEmpty(password) || isEmailEmpty(email)
    }
}