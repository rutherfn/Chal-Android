package com.nicholasrutherford.chal.create.account.createaccount

interface CreateAccountViewState  {
    var toolbarText: String
    var usernameErrorVisible: Boolean
    var emailErrorVisible: Boolean
    var passwordErrorVisible: Boolean
}