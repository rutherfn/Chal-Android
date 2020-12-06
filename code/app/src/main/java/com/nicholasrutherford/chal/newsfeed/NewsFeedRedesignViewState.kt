package com.nicholasrutherford.chal.newsfeed

import com.nicholasrutherford.chal.viewstate.ViewState

interface NewsFeedRedesignViewState : ViewState {
    var toolbarName: String
    var toolbarImage: String
}