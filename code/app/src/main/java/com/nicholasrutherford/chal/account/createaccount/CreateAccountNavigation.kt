package com.nicholasrutherford.chal.account.createaccount

interface CreateAccountNavigation {
    fun login(createAccountActivity: CreateAccountActivity)
    fun uploadPhoto(userName: String, email: String, password: String, createAccountActivity: CreateAccountActivity)
    fun showAcProgress(createAccountActivity: CreateAccountActivity)
    fun hideAcProgress()
    fun errorCreate(errorMessageText: String, createAccountActivity: CreateAccountActivity)
}