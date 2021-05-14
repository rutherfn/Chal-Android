package com.nicholasrutherford.chal.navigationimpl.newsfeed

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import com.nicholasrutherford.chal.newsfeed.NewsFeedNavigation
import com.nicholasrutherford.chal.people.SearchPeopleFragment
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity
import javax.inject.Inject

class NewsFeedNavigationImpl @Inject constructor(private val application: Application, private val newsFeedFragment: NewsFeedFragment) : NewsFeedNavigation {

    override fun showSearchPeopleFragment() {
        val bundle = Bundle()
        bundle.putBoolean("isBackStack", true)

        val searchPeopleFragment = SearchPeopleFragment(application)
        searchPeopleFragment.arguments = bundle

        newsFeedFragment.fragmentManager?.beginTransaction()
            ?.replace(
                container,
                searchPeopleFragment,
                searchPeopleFragment::javaClass.name
            )
            ?.addToBackStack("searchPeopleFragment")
            ?.commit()
    }

    override fun showPeopleList() {
        newsFeedFragment.fragmentManager?.beginTransaction()
            ?.replace(
                container,
                PeopleListFragment(application),
                PeopleListFragment(application)::javaClass.name
            )
            ?.addToBackStack("")
            ?.commit()
    }

    override fun showAlert(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(application.applicationContext)

        alertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(newsFeedFragment.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(title)
        alert.show()
    }

    override fun showProgress() {
        val intent = Intent(application.applicationContext, ProgressUploadActivity::class.java)
        newsFeedFragment.startActivity(intent)
    }
}
