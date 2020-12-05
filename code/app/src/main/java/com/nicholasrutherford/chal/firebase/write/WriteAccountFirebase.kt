package com.nicholasrutherford.chal.firebase.write

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.firebase.*
import com.nicholasrutherford.chal.firebase.sharedpref.ReadFirebaseSharePref
import com.nicholasrutherford.chal.firebase.sharedpref.WriteFirebaseSharedPref

class WriteAccountFirebase(appContext: Context) {

    private val writeFirebaseSharePref = WriteFirebaseSharedPref(appContext)
    private val readFirebaseSharedPref = ReadFirebaseSharePref(appContext)

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    fun updateUsername(username: String?) {
        ref.child("$uid/$USERNAME").setValue(username).addOnSuccessListener {
            username?.let { newUsername ->
                writeFirebaseSharePref.writeSharedPrefsUsername(newUsername)
            }
        }
    }

    fun updateFirstName(firstName: String?) {
        ref.child("$uid/$FIRST_NAME").setValue(firstName).addOnSuccessListener {
            firstName?.let { newFirstName ->
                writeFirebaseSharePref.writeSharePrefsFirstName(newFirstName)
            }
        }
    }

    fun updateLastName(lastName: String?) {
        ref.child("$uid/$LAST_NAME").setValue(lastName).addOnSuccessListener {
            lastName?.let { newLastName ->
                writeFirebaseSharePref.writeSharedPrefsLastName(newLastName)
            }
        }
    }

    fun updateBio(bio: String?) {
        ref.child("$uid/$BIO").setValue(bio).addOnSuccessListener {
            bio?.let { newBio ->
                writeFirebaseSharePref.writeSharePrefsBio(newBio)
            }
        }
    }


    fun updateAge(age: Int) {
        ref.child("$uid/$AGE").setValue(age).addOnSuccessListener {
            writeFirebaseSharePref.writeSharedPrefsAge(age)
            // it worked do something here
        }
            .addOnFailureListener {
                // it failed don't do something here
            }
    }
}