package com.nicholasrutherford.chal.main.upload.progress

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.*

class WriteActivePostImpl {

    private val refPosts = FirebaseDatabase.getInstance().getReference(POSTS)
    private val refUsers = FirebaseDatabase.getInstance().getReference(USERS)

    fun writeTitle(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            titleActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(titlePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeDescription(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            descriptionActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(descriptionPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeCategory(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: Int
    ) {
        refUsers.child(
            categoryActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(categoryPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeImage(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            imageActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(imagePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeCurrentDay(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            currentDayActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengeIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(currentDayPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeUsername(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            usernameActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(usernamePath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeUsernameUrl(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String
    ) {
        refUsers.child(
            usernameUrlActiveChallengeUserPostPath(
                uid,
                userActiveChallengeIndex,
                userActiveChallengePostIndex
            )
        )
            .setValue(newValue)
            .addOnFailureListener { }

        refPosts.child(usernameUrlPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writeCurrentChallengeExpireDay(uid: String, postIndex: Int, newValue: String) {
        refPosts.child(currentChallengeExpireDayPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener { }
    }

    fun writePost(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        activePost: ActivePost
    ) {
        writeTitle(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.title
        )
        writeDescription(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.description
        )
        writeCategory(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.category
        )
        writeImage(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.image
        )
        writeCurrentDay(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.currentDay
        )
        writeUsername(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.username
        )
        writeUsernameUrl(
            uid = uid,
            userActiveChallengeIndex = userActiveChallengeIndex,
            userActiveChallengePostIndex = userActiveChallengePostIndex,
            postIndex = postIndex,
            newValue = activePost.usernameUrl
        )
        writeCurrentChallengeExpireDay(
            uid = uid,
            postIndex = postIndex,
            newValue = activePost.dateChallengeExpired
        )
    }
}