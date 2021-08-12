package com.nicholasrutherford.chal.firebase.write.activepost

import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteActivePost : ChalFirebase {
    fun writeTitle(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeDescription(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeCategory(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: Int)
    fun writeImage(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeCurrentDay(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeUsername(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writeUsernameUrl(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, newValue: String)
    fun writePost(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int, postIndex: Int, activePost: ActivePost)
}