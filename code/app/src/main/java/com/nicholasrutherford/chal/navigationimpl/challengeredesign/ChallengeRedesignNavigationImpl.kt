package com.nicholasrutherford.chal.navigationimpl.challengeredesign

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.challengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignNavigation
import com.nicholasrutherford.chal.data.realdata.Challenges

const val container = R.id.container

class ChallengeRedesignNavigationImpl : ChallengesRedesignNavigation {

    override fun showAlert(message: String, title: String, fragmentActivity: FragmentActivity) {
        val alertDialogBuilder = AlertDialog.Builder(fragmentActivity)

        alertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        var alert = alertDialogBuilder.create()

        alert.setTitle(title)
        alert.show()
    }

    override fun showChallengeDetails(fragmentManager: FragmentManager, context: Context, challenge: Challenges) {
        fragmentManager.beginTransaction()
            .replace(
                container,
                challengeDetailsFragment(context, challenge),
                challengeDetailsFragment(context, challenge)::javaClass.name
            )
            .commit()
    }
}
