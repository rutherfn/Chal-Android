package com.nicholasrutherford.chal.firebase.storage

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class ChalFirebaseStorageImpl @Inject constructor(): ChalFirebaseStorage {

    override val storage = FirebaseStorage.getInstance()

    override fun profilePictureImageReference(fileName: String): StorageReference {
        return storage.getReference("/images/$fileName")
    }
}