package com.nicholasrutherford.chal.newsfeed

interface NewsFeedNavigation {
    fun showPeopleList()
    fun showAlert(title: String, message: String)
    fun showProgress()
}
