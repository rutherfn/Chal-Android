package com.nicholasrutherford.chal.challenge.list

import con.nicholasrutherford.chal.data.challenges.ActiveChallenge
import con.nicholasrutherford.chal.data.challenges.AvailableChallenges

interface ChallengeListNavigation {
    fun showChallengeDetail(selectedAvailableChallenge: AvailableChallenges)
}