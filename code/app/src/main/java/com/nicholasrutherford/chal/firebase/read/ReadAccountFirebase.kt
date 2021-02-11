package com.nicholasrutherford.chal.firebase.read

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.data.realdata.ActiveChallenges
import com.nicholasrutherford.chal.data.realdata.CurrentFriends
import com.nicholasrutherford.chal.firebase.AGE
import com.nicholasrutherford.chal.firebase.BIO
import com.nicholasrutherford.chal.firebase.DEFAULT_PROFILE_IMAGE
import com.nicholasrutherford.chal.firebase.EMAIL
import com.nicholasrutherford.chal.firebase.FIRST_NAME
import com.nicholasrutherford.chal.firebase.ID
import com.nicholasrutherford.chal.firebase.LAST_NAME
import com.nicholasrutherford.chal.firebase.PASSWORD
import com.nicholasrutherford.chal.firebase.PROFILE_IMAGE
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.sharedpref.ReadFirebaseSharePref
import com.nicholasrutherford.chal.firebase.sharedpref.WriteFirebaseSharedPref

class ReadAccountFirebase(appContext: Context) : FirebaseReadExtension {

    private val writeFirebaseSharePref = WriteFirebaseSharedPref(appContext)
    private val readFirebaseSharedPref = ReadFirebaseSharePref(appContext)

    private val currentFirendsList: ArrayList<CurrentFriends> = ArrayList()

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun getAge(): Int? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsAge(0)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(AGE).value.toString()
                    writeFirebaseSharePref.writeSharedPrefsAge(ageSnapshot.toInt())
                } else {
                    writeFirebaseSharePref.writeSharedPrefsAge(0)
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseAge()
    }

    override fun getBio(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharePrefsBio("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(BIO).value.toString()

                    writeFirebaseSharePref.writeSharePrefsBio(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharePrefsBio("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseBio()
    }

    override fun getEmail(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsEmail("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(EMAIL).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsEmail(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharedPrefsEmail("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseEmail()
    }

    override fun getFirstName(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharePrefsFirstName("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(FIRST_NAME).value.toString()

                    writeFirebaseSharePref.writeSharePrefsFirstName(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharePrefsFirstName("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseFirstName()
    }

    override fun getId(): Int? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsId(0)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(ID).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsId(ageSnapshot.toInt())
                } else {
                    writeFirebaseSharePref.writeSharedPrefsId(0)
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseId()
    }

    override fun getLastName(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsLastName("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(LAST_NAME).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsLastName(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharedPrefsLastName("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseLastName()
    }

    override fun getPassword(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsPassword("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(PASSWORD).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsPassword(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharedPrefsPassword("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebasePassword()
    }

    override fun getUserProfilePicture(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsUserProfilePicture(DEFAULT_PROFILE_IMAGE)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(PROFILE_IMAGE).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsUserProfilePicture(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharedPrefsUserProfilePicture(DEFAULT_PROFILE_IMAGE)
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseUserProfilePicture()
    }

    override fun getUsername(): String? {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                writeFirebaseSharePref.writeSharedPrefsUsername("")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val ageSnapshot = snapshot.child(USERNAME).value.toString()

                    writeFirebaseSharePref.writeSharedPrefsUsername(ageSnapshot)
                } else {
                    writeFirebaseSharePref.writeSharedPrefsUsername("")
                }
            }
        })
        return readFirebaseSharedPref.getSharedPrefsFirebaseUsername()
    }

    override fun getActiveUserChallenges() {
        ref.child("$uid/activeChallenges").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val activeChallengesList: ArrayList<ActiveChallenges> = ArrayList()
                if (snapshot.exists()) {
                    for (activeChallenge in snapshot.children) {
                        val userChallenge = activeChallenge.getValue(ActiveChallenges::class.java)
                        println(userChallenge!!.description)
                        activeChallengesList.add(userChallenge!!)
                    }
                } else {
                    println("does not exist")
                }
            }
        })
    }

    override fun getCurrentUserFriends(): List<CurrentFriends> {
        return currentFirendsList
    }
}