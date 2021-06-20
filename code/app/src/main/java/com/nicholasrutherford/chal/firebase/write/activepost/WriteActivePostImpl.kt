package com.nicholasrutherford.chal.firebase.write.activepost

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.CATEGORY
import com.nicholasrutherford.chal.firebase.CURRENT_DAY
import com.nicholasrutherford.chal.firebase.DESCRIPTION
import com.nicholasrutherford.chal.firebase.IMAGE
import com.nicholasrutherford.chal.firebase.POSTS
import com.nicholasrutherford.chal.firebase.TITLE
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERNAME_URL
import com.nicholasrutherford.chal.firebase.USERS

class WriteActivePostImpl : WriteActivePost {

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val refPosts = FirebaseDatabase.getInstance().getReference(POSTS)
    private val refUsers = FirebaseDatabase.getInstance().getReference(USERS)

    private fun parentDirectoryChallengePost(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int): String {
        return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex"
    }

    private fun parentDirectoryPost(postIndex: Int): String {
        return "$postIndex/"
    }

    override fun writeTitle(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(TITLE).setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(TITLE).setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeDescription(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(DESCRIPTION).setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(DESCRIPTION).setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCategory(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: Int) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(CATEGORY).setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(CATEGORY).setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeImage(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(IMAGE).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(IMAGE).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun writeCurrentDay(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengeIndex))
            .child(CURRENT_DAY).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(CURRENT_DAY).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun writeUsername(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(USERNAME).setValue(newValue)
            .addOnFailureListener {  }
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(USERNAME).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun writeUsernameUrl(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(parentDirectoryChallengePost(userActiveChallengeIndex, userActiveChallengePostIndex))
            .child(USERNAME_URL).setValue(newValue)
            .addOnFailureListener {  }
            .addOnFailureListener {  }

        refPosts.child(parentDirectoryPost(postIndex))
            .child(USERNAME_URL).setValue(newValue)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun writePost(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        activePost: ActivePost) {
        writeTitle(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.title
        )
        writeDescription(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.description
        )
        writeCategory(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.category
        )
        writeImage(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.image
        )
        writeCurrentDay(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.currentDay
        )
        writeUsername(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.username
        )
        writeUsernameUrl(
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.usernameUrl
        )
    }
}