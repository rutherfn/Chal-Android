package com.nicholasrutherford.chal.account.redesignlogin

import com.nicholasrutherford.chal.Navigation

interface RedesignLoginViewNavigation: Navigation {
    fun signUp()
    fun forgotPassword()
    fun errorLogin(errorMessageText: String)
    fun showAcProgress()
    fun hideAcProgress()
    fun loginToApp()
}