package com.nicholasrutherford.chal.challengesredesign.challengedetails

import com.nicholasrutherford.chal.ViewState

interface ChallengeDetailsViewState : ViewState {
    var toolbarName: String
    var toolbarImage: String
}