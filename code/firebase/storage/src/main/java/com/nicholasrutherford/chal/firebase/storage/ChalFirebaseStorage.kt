package com.nicholasrutherford.chal.firebase.storage

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

interface ChalFirebaseStorage {
    val storage: FirebaseStorage
    fun profilePictureImageReference(fileName: String): StorageReference
}