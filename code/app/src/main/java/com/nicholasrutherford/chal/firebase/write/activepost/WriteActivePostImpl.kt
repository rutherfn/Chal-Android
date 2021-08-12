package com.nicholasrutherford.chal.firebase.write.activepost

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.firebase.POSTS
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.categoryActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.categoryPostPath
import com.nicholasrutherford.chal.firebase.currentChallengeExpireDayPath
import com.nicholasrutherford.chal.firebase.currentDayActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.currentDayPath
import com.nicholasrutherford.chal.firebase.descriptionActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.descriptionPostPath
import com.nicholasrutherford.chal.firebase.imageActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.imagePostPath
import com.nicholasrutherford.chal.firebase.titleActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.titlePostPath
import com.nicholasrutherford.chal.firebase.usernameActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.usernamePath
import com.nicholasrutherford.chal.firebase.usernameUrlActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.usernameUrlPath

class WriteActivePostImpl : WriteActivePost {

    private val refPosts = FirebaseDatabase.getInstance().getReference(POSTS)
    private val refUsers = FirebaseDatabase.getInstance().getReference(USERS)

    override fun writeTitle(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(titleActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(titlePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeDescription(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(descriptionActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(descriptionPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCategory(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: Int) {
        refUsers.child(categoryActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(categoryPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeImage(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(imageActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(imagePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCurrentDay(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(currentDayActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(currentDayPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeUsername(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(usernameActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(usernamePath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeUsernameUrl(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(usernameUrlActiveChallengeUserPostPath(uid, userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(usernameUrlPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCurrentChallengeExpireDay(uid: String, postIndex: Int, newValue: String) {
        refPosts.child(currentChallengeExpireDayPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writePost(
        uid: String,
        userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        activePost: ActivePost) {
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