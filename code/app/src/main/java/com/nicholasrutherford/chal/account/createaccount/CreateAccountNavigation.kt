package com.nicholasrutherford.chal.account.createaccount

interface CreateAccountNavigation {
    fun signUp(createAccountActivity: CreateAccountActivity)
    fun uploadPhoto(userName: String, email: String, password: String, createAccountActivity: CreateAccountActivity)
    fun showAcProgress(createAccountActivity: CreateAccountActivity)
    fun hideAcProgress()
}