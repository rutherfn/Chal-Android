package com.nicholasrutherford.chal.create.account.createaccount

interface CreateAccountNavigation {
    fun showUploadPhoto(username: String, email: String, password: String)
    fun pop()
}