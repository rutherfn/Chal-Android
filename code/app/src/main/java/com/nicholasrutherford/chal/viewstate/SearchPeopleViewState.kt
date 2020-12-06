package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.ViewState
import com.nicholasrutherford.chal.data.responses.SearchPeople

interface SearchPeopleViewState : ViewState {
    val searchPeopleList: ArrayList<SearchPeople>
    val searchPeopleClicked: Boolean
}