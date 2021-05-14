package com.nicholasrutherford.chal.people

import com.nicholasrutherford.chal.ViewState

interface SearchPeopleViewState : ViewState {
    var toolbarName: String
    var toolbarUrl: String
    var profileUrl: String
    var profileName: String
}