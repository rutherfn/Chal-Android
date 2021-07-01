package com.nicholasrutherford.chal.firebase.read.accountinfo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.data.responses.post.PostResponse
import com.nicholasrutherford.chal.firebase.POSTS
import com.nicholasrutherford.chal.firebase.USERS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReadFirebaseFieldsImpl : ReadFirebaseFields {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val refPosts = FirebaseDatabase.getInstance().getReference(POSTS)

    private val uid = FirebaseAuth.getInstance().uid ?: ""


    override fun getAccountInfo() {
        ref.child(uid).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    suspend fun something() {

    }

    override fun getAllPosts(_postList: MutableStateFlow<List<PostListResponse>>) {
        println("start get something")
        refPosts.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) = Unit

            override fun onDataChange(snapshot: DataSnapshot) {
                println("start get something 1")
                val postList = arrayListOf<PostListResponse>()
                for (posts in snapshot.children) {
                    posts.getValue(PostResponse::class.java).let { postResponse ->
                        postResponse?.let { data ->
                            postList.add(PostListResponse(data))
                        }
                    }
                }
                println("start get something 2")
                _postList.value = postList
            }

        })
    }
}