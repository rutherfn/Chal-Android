package com.nicholasrutherford.chal.challenge.list

import androidx.core.os.bundleOf
import com.nicholasrutherford.chal.helper.constants.*
import com.nicholasrutherford.chal.main.navigation.Navigator
import con.nicholasrutherford.chal.data.challenges.AvailableChallenges
import javax.inject.Inject

class ChallengeListNavigationImpl @Inject constructor(): ChallengeListNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showChallengeDetail(selectedAvailableChallenge: AvailableChallenges) {
        val challengeDetailBundle =
            bundleOf(
                CHALLENGE_TITLE to selectedAvailableChallenge.title,
                CHALLENGE_CATEGORY to selectedAvailableChallenge.category,
                CHALLENGE_URL to selectedAvailableChallenge.url,
                CHALLENGE_DESC to selectedAvailableChallenge.desc,
                CHALLENGE_TIME to selectedAvailableChallenge.time,
                CHALLENGE_DURATION to selectedAvailableChallenge.duration,
                CHALLENGE_CATEGORY_NUMBER to selectedAvailableChallenge.categoryNumber
            )

        navigator.navigateWithBundle(challengeDetailBundle, R.id.nav_graph_challenge_detail)
    }
}