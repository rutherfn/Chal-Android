package com.nicholasrutherford.chal.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.data.responses.ChallengeResponse
import com.nicholasrutherford.chal.recycler.adapters.SearchChallenges
import com.nicholasrutherford.chal.recycler.adapters.ActiveChallenges

class ChallengesFragment : Fragment() {

    private var mView: View? = null

    private var activeChallengesList: MutableList<ChallengeResponse> = ArrayList()
    private var searchChallengesList: MutableList<ChallengeResponse> = ArrayList()

    private var challengeResponseOne = ChallengeResponse(0, "#100 Days Of Coding Challenge TEST", "For this challenge, there are two main rules:\n\n1. Code for a minimum an hour every day for the next 100 days. \\n2. Post your progress every day with the #100DaysOfCode hashtag. \n\nBuild something cool and have fun coding! (:", "Software Development", "\"https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
    private var challengeResponseTwo = ChallengeResponse(1, "30 Days Cook Something New Challenge ", "For the next 30 days we want to see what you got in the kitchen! Cook up something good, and share it with a community of supportive individuals.You can either share a video, or a picture of your home cook meal, lets have fun!", "Cooking", "https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
    private var challengeResponseThree = ChallengeResponse(2, "30 Days of Nature", "Get outside! Research shows that only 10 mins of being outside each day makes you happier.", "Mental", "https://snpha.org/images/MH.png")

    private var challengeResponseFour = ChallengeResponse(0, "#100 Days Of Coding Challenge TEST", "For this challenge, there are two main rules:\n\n1. Code for a minimum an hour every day for the next 100 days. \\n2. Post your progress every day with the #100DaysOfCode hashtag. \n\nBuild something cool and have fun coding! (:", "Software Development", "\"https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
    private var challengeResponseFive = ChallengeResponse(1, "30 Days Cook Something New Challenge ", "For the next 30 days we want to see what you got in the kitchen! Cook up something good, and share it with a community of supportive individuals.You can either share a video, or a picture of your home cook meal, lets have fun!", "Cooking", "https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
    private var challengeResponseSix = ChallengeResponse(2, "30 Days of Nature", "Get outside! Research shows that only 10 mins of being outside each day makes you happier.", "Mental", "https://snpha.org/images/MH.png")


    // adapters
    private var adapterActiveChallenges: ActiveChallenges? = null
    private var adapterSearchChallenges: SearchChallenges? = null

    private lateinit var topNavChallenges: BottomNavigationView
    private lateinit var rvChallenges: RecyclerView
    private lateinit var svChallenge: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_challenges, container, false)
        main()
        return mView
    }

    private fun main() {

        setupIds()

        setupTopNav()

        setRecyclerToActiveChallengesAdapt()

        onRecyclerClickListener()
    }

    private fun setupIds() {
        topNavChallenges = mView!!.findViewById(R.id.topNavChallenges)
        rvChallenges = mView!!.findViewById(R.id.rvChallenges)
        svChallenge = mView!!.findViewById(R.id.svChallenges)
    }

    private fun setupTopNav() {
        topNavChallenges.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.navigation_your_challenges -> {
                        if(adapterActiveChallenges != null) {
                            setRecyclerToActiveChallengesAdapt()
                            svChallenge.visibility = View.GONE
                        }
                        return true
                    }
                    R.id.navigation_search_challenges -> {
                        setRecyclerToSearchChallengesAdapt()
                        svChallenge.visibility = View.VISIBLE
                        return true
                    }

                }
                return false
            }
        }

    private fun fakeData() {

        activeChallengesList.clear()

        activeChallengesList.add(challengeResponseOne)
        activeChallengesList.add(challengeResponseTwo)
        activeChallengesList.add(challengeResponseThree)
    }

    private fun fakeDataForSearch() {

        searchChallengesList.clear()

        searchChallengesList.add(challengeResponseOne)
        searchChallengesList.add(challengeResponseTwo)
        searchChallengesList.add(challengeResponseThree)
        searchChallengesList.add(challengeResponseFour)
        searchChallengesList.add(challengeResponseFive)
        searchChallengesList.add(challengeResponseSix)
    }

    private fun setupRecyclerViewForChallengesAdapt() {

        rvChallenges.adapter = null

        adapterActiveChallenges = null

        rvChallenges.isNestedScrollingEnabled = false
        rvChallenges.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupRecyclerViewForSearchChallengesAdapt() {
        rvChallenges.adapter = null

        adapterSearchChallenges = null

        rvChallenges.isNestedScrollingEnabled = false
        rvChallenges.layoutManager = GridLayoutManager(activity, 3)
    }

    private fun setRecyclerToActiveChallengesAdapt() {

        setupRecyclerViewForChallengesAdapt()

        fakeData()

        println("Active Challenge")
        adapterActiveChallenges = context?.let {
            ActiveChallenges(
                it,
                activeChallengesList
            )
        }
        rvChallenges.adapter = adapterActiveChallenges
    }

    private fun setRecyclerToSearchChallengesAdapt() {

        setupRecyclerViewForSearchChallengesAdapt()

        fakeDataForSearch()

        adapterSearchChallenges = context?.let {
            SearchChallenges(
                it,
                searchChallengesList
            )
        }
        rvChallenges.adapter = adapterSearchChallenges
    }

    private fun onRecyclerClickListener() {

            svChallenge.setOnClickListener {
                context?.let {
                    val intent = Intent(it.applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}