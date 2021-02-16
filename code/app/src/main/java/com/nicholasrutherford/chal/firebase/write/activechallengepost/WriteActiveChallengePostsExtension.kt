package com.nicholasrutherford.chal.firebase.write.activechallengepost

interface WriteActiveChallengePostsExtension {
    fun parentDirectoryChallengePost(selectedIndex: Int, index: Int): String
    fun writeTitle(selectedIndex: Int, index: Int, newValue: String)
    fun writeDescription(selectedIndex: Int, index: Int, newValue: String)
    fun writeCategory(selectedIndex: Int, index: Int, newValue: Int)
    fun writeImage(selectedIndex: Int, index: Int, newValue: String)
    fun writeCurrentDay(selectedIndex: Int, index: Int, newValue: String)
    fun writeUsername(selectedIndex: Int, index: Int, newValue: String)
    fun writeUsernameUrl(selectedIndex: Int, index: Int, newValue: String)
}
