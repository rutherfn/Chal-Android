package com.nicholasrutherford.chal.challengesredesign.challengedetails

import com.nicholasrutherford.chal.viewstate.ViewState

interface ChallengeDetailsViewState : ViewState {
    var toolbarName: String
    var toolbarImage: String
}