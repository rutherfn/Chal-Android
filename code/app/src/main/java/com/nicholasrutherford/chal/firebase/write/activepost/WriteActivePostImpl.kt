package com.nicholasrutherford.chal.firebase.write.activepost

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.firebase.POSTS
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.categoryActiveChallengeUserPostPath
import com.nicholasrutherford.chal.firebase.categoryPostPath
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

    override fun writeTitle(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(titleActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(titlePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeDescription(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(descriptionActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(descriptionPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCategory(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: Int) {
        refUsers.child(categoryActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(categoryPostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeImage(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(imageActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(imagePostPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeCurrentDay(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(currentDayActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(currentDayPath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeUsername(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(usernameActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(usernamePath(postIndex))
            .setValue(newValue)
            .addOnFailureListener {  }
    }

    override fun writeUsernameUrl(userActiveChallengeIndex: Int,
        userActiveChallengePostIndex: Int,
        postIndex: Int,
        newValue: String) {
        refUsers.child(usernameUrlActiveChallengeUserPostPath(userActiveChallengeIndex, userActiveChallengePostIndex))
            .setValue(newValue)
            .addOnFailureListener {  }

        refPosts.child(usernameUrlPath(postIndex))
            .setValue(newValue)
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