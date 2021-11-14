package com.nicholasrutherford.chal.firebase.realtime.database.create

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.*
import con.nicholasrutherford.chal.data.challenges.ActiveChallenge
import javax.inject.Inject

// TODO we need to add on failure timber logs as we ass sucess timber logs in the future!
class CreateFirebaseDatabaseImpl @Inject constructor(
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val firebaseAuth: ChalFirebaseAuth
): CreateFirebaseDatabase {

    override val uid = firebaseAuth.uid

    override val databaseReferenceUsers = FirebaseDatabase.getInstance()
        .getReference(USERS)
    override val databaseReferenceActiveChallenges = FirebaseDatabase.getInstance()
        .getReference(USERS + ROUTE_ALL_ACTIVE_CHALLENGES)

    private fun createNameOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        name: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(nameOfChallengePath(firebaseUid, userChallengeIndex))
                .setValue(name)
                .addOnFailureListener {
                    // TODO timber log here
                }
        }

        databaseReferenceActiveChallenges.child(nameAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(name)
            .addOnFailureListener {
                // TODO timber log here
            }
    }

    private fun createBioOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        bio: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(bioActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(bio)
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(bioAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(bio)
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    private fun createCategoryNameOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        categoryName: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(categoryNameActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(categoryName)
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(categoryAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(categoryName)
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    private fun createDaysOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        days: Int
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(daysInChallengeActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(days)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(daysInChallengeAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(days)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    private fun creatDateChallengeExpireOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        dateChallengeExpires: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(dateChallengeExpireActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(dateChallengeExpires)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(dateChallengeExpireAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(dateChallengeExpires)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    override fun createCurrentDayOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        currentDay: Int
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(currentDayActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(currentDay)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(currentDayAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(currentDay)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    private fun createUsernameEnrolledOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        username: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(usernameActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(username)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(usernameAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(username)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    private fun createLastDateOfChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        lastDateOfChallenge: String
    ) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(lastDateOfActiveChallengePath(firebaseUid, userChallengeIndex))
                .setValue(lastDateOfChallenge)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }

            databaseReferenceActiveChallenges.child(lastDateAllActiveChallengePath(allActiveChallengeIndex))
                .setValue(lastDateOfChallenge)
                .addOnSuccessListener {
                    // TODO timber log here
                }
                .addOnFailureListener {
                    // TODO timber log here
                }
        }
    }

    override fun createNewActiveChallenge(
        allActiveChallengeIndex: Int,
        userChallengeIndex: String,
        activeChallenge: ActiveChallenge
    ) {
        createNameOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.name)
        createBioOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.bio)
        createCategoryNameOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.categoryName)

        createDaysOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.numberOfDaysInChallenge)
        creatDateChallengeExpireOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.challengeExpire)
        createCurrentDayOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.currentDay)
        createUsernameEnrolledOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.username)
        createLastDateOfChallenge(allActiveChallengeIndex, userChallengeIndex, activeChallenge.lastDateOfChallenge)
    }

    override fun createChallengeBannerType(bannerType: Int) {
        uid?.let { firebaseUid ->
            fetchFirebaseDatabase.databaseUserReference
                .child(challengeBannerTypePath(firebaseUid))
                .setValue(bannerType)
                .addOnSuccessListener {
                // timber log here }
                }
                .addOnFailureListener {
                    // timber log here
                }
        }
    }

    override fun createUsername(username: String) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(usernameAccountPath(firebaseUid)).setValue(username)
                .addOnFailureListener {
                   // Timber.d("Error writing Firebase field $username to ${usernameAccountPath(uid)}")
                }
        }
    }

    override fun createFirstName(firstName: String) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(firstNameAccountPath(firebaseUid)).setValue(firstName)
                .addOnFailureListener {
                    // Timber.d("Error writing Firebase field $firstName to ${firstNameAccountPath(uid)}")
                }
        }
    }

    override fun createLastName(lastName: String) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(lastNameAccountPath(firebaseUid)).setValue(lastName)
                .addOnSuccessListener {
                    // Timber.d("Error writing Firebase field $lastName to ${lastNameAccountPath(uid)}")
                }
        }
    }

    override fun createBio(bio: String) {
        uid?.let { firebaseUid ->
            databaseReferenceUsers.child(bioAccountPath(firebaseUid)).setValue(bio)
                .addOnSuccessListener {
                    // Timber.d("Error writing Firebase field $bio to ${bioAccountPath(uid)}")
                }
        }
    }

    override fun createAccountinfo(
        username: String,
        firstName: String,
        lastName: String,
        bio: String
    ) {
        createUsername(username)
        createFirstName(firstName)
        createLastName(lastName)
        createBio(bio)
    }
}