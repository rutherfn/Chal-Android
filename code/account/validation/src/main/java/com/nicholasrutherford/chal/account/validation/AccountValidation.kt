package com.nicholasrutherford.chal.account.validation

interface AccountValidation {
    fun isEmailEmpty(email: String): Boolean
    fun isEmailMeetRequirements(email: String): Boolean
    fun isEmailValid(email: String): Boolean
    fun isPasswordEmpty(password: String): Boolean
    fun isPasswordOrEmailEmpty(email: String, password: String): Boolean
}