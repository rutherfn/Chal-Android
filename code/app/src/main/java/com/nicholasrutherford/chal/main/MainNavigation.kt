package com.nicholasrutherford.chal.main

import com.nicholasrutherford.chal.Navigation

interface MainNavigation : Navigation {
    fun showChallenges()
    fun showMewsFeed(backStack: String?)
    fun showMore()
}