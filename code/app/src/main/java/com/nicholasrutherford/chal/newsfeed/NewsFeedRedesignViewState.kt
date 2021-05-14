package com.nicholasrutherford.chal.newsfeed

import com.nicholasrutherford.chal.ViewState

interface NewsFeedRedesignViewState : ViewState {
    var myChallengesVisible: Boolean
    var toolbarName: String
    var toolbarImage: String
    var isEndOfNewsFeedVisible: Boolean
}
