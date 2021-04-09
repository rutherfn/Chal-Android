package com.nicholasrutherford.chal.peoplelist

import com.nicholasrutherford.chal.ViewState

interface PeopleListViewState : ViewState {
    var profileImageUrl: String
    var profileUsername: String
}
