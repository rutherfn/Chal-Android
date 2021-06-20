package com.nicholasrutherford.chal.firebase.write.activepost

import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteActivePost : ChalFirebase {
    fun writeTitle(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeDescription(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeCategory(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: Int)
    fun writeImage(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeCurrentDay(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeUsername(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeUsernameUrl(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writePost(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, activePost: ActivePost)
}