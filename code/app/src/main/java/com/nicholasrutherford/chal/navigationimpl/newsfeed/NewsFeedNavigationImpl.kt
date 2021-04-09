package com.nicholasrutherford.chal.navigationimpl.newsfeed

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedNavigation
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity

class NewsFeedNavigationImpl : NewsFeedNavigation {

    override fun showPeopleList(mainActivity: FragmentActivity, fragmentManager: FragmentManager, context: Context) {
        fragmentManager.beginTransaction()
            .replace(
                container,
                PeopleListFragment(mainActivity.application),
                PeopleListFragment(mainActivity.application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

    override fun showAlert(title: String, message: String, fragmentActivity: FragmentActivity) {
        val alertDialogBuilder = AlertDialog.Builder(fragmentActivity)

        alertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(title)
        alert.show()
    }

    override fun showProgress(fragmentActivity: FragmentActivity, context: Context) {
        val intent = Intent(context, ProgressUploadActivity::class.java)
        fragmentActivity.startActivity(intent)
    }
}
