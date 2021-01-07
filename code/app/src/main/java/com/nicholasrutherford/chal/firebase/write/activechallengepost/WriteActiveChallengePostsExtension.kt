package com.nicholasrutherford.chal.firebase.write.activechallengepost

interface WriteActiveChallengePostsExtension {
    fun parentDirectoryChallengePost(index: Int): String
    fun writeTitle(index: Int, newValue: String)
    fun writeDescription(index: Int, newValue: String)
    fun writeCategory(index: Int, newValue: Int)
    fun writeImage(index: Int, newValue: String)
    fun writeCurrentDay(index: Int, newValue: String)
}