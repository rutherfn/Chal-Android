package com.nicholasrutherford.chal.account.redesignlogin

import com.nicholasrutherford.chal.ViewState

interface RedesignLoginViewState : ViewState {
    var emailErrorImageVisible: Boolean
    var emailErrorTextVisible: Boolean
}