package com.nicholasrutherford.chal.newsfeed

interface NewsFeedNavigation {
    fun hideAcProgress()
    fun pop()
    fun showAcProgress()
    fun showPeopleList()
    fun showAlert(title: String, message: String)
    fun showProgress()
}
