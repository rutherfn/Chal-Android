package com.nicholasrutherford.chal.challenge.detail

import con.nicholasrutherford.chal.data.challenges.AvailableChallenges

interface ChallengeDetailNavigation {
    fun showRelatedChallengeDetail(selectedAvailableChallenge: AvailableChallenges)
    fun onNavigateBack()
}