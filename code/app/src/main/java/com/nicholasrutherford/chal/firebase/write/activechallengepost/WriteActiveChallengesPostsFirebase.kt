package com.nicholasrutherford.chal.firebase.write.activechallengepost

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.firebase.*

class WriteActiveChallengesPostsFirebase () : WriteActiveChallengePostsExtension {

    private val starterIndex = "0"
    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun parentDirectoryChallengePost(index: Int): String {
        return "$uid$ACTIVE_CHALLENGES$starterIndex$ACTIVE_CHALLENGES_POSTS$index"
    }

    override fun writeTitle(index: Int, newValue: String) {
        ref.child(parentDirectoryChallengePost(index)).
        child(TITLE_ACTIVE_CHALLENGES_POST).setValue(newValue)
    }

    override fun writeDescription(index: Int, newValue: String) {
        ref.child(parentDirectoryChallengePost(index)).
        child(DESCRIPTION_ACTIVE_CHALLENGES_POST).setValue(newValue)
    }

    override fun writeCategory(index: Int, newValue: Int) {
        ref.child(parentDirectoryChallengePost(index)).
        child(CATEGORY_ACTIVE_CHALLENGES_POST).setValue(newValue)
    }

    override fun writeImage(index: Int, newValue: String) {
        ref.child(parentDirectoryChallengePost(index)).
        child(CATEGORY_ACTIVE_CHALLENGES_POST).setValue(newValue)
    }

    override fun writeCurrentDay(index: Int, newValue: String) {
        ref.child(parentDirectoryChallengePost(index)).
        child(CURRENT_DAY_ACTIVE_CHALLENGES_POST).setValue(newValue)
    }


}