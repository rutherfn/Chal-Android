package com.nicholasrutherford.chal.challenge.list

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class ChallengeListNavigationImpl @Inject constructor(): ChallengeListNavigation {

    @Inject
    lateinit var navigator: Navigator
}