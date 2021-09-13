package com.nicholasrutherford.chal.account.validation

import android.app.Application
import java.util.*
import javax.inject.Inject

@Suppress("MagicNumber")
const val AT_SYMBOL = "@"

class AccountValidationImpl @Inject constructor(private val application: Application) : AccountValidation {

    private val empty  = application.getString(R.string.empty_string)
    private val dotCom = application.getString(R.string.dot_com)

    override fun isUsernameEmpty(username: String): Boolean {
        return username == empty
    }

    override fun isPasswordContainsDigit(password: String): Boolean {
        for (c in password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true
            }
        }
        return false
    }

    override fun isUpperCasePassword(password: String): Boolean {
        return password != password.toLowerCase(Locale.ROOT)
    }

    override fun isEmailEmpty(email: String): Boolean {
        return email == empty
    }

    override fun isEmailMeetRequirements(email: String): Boolean {
        return email.contains(AT_SYMBOL) && email.contains(dotCom)
    }

    override fun isEmailValid(email: String): Boolean {
        return !isEmailEmpty(email) && isEmailMeetRequirements(email)
    }

    override fun isPasswordEmpty(password: String): Boolean {
        return password == empty
    }

    override fun isPasswordOrEmailEmpty(email: String, password: String) : Boolean {
        return isPasswordEmpty(password) || isEmailEmpty(email)
    }
}