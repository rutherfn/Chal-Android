package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.data.responses.SearchPeople
import com.nicholasrutherford.chal.fragments.OtherUserProfileFragment
import com.nicholasrutherford.chal.navigation.searchPeople.SearchPeopleNavigationImpl
import com.nicholasrutherford.chal.viewstate.SearchPeopleViewState

class SearchPeopleViewModel(private val bottomNavigationView: BottomNavigationView, private val context: Context, private val fragmentManager: FragmentManager, private val containerId: Int, private val fragment: OtherUserProfileFragment) : ViewModel() {
    private val searchPeopleNavigationImpl = SearchPeopleNavigationImpl()
    val viewState = SearchPeopleViewStateImpl()

    fun returnSampleDataOfPeople(): ArrayList<SearchPeople> {
        val data = ArrayList<SearchPeople>()
        val searchOne = SearchPeople("Nick", "Rutherford",  "Because you like fitness", "https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg")
        val searchFive = SearchPeople("Patrick", "McGinn", "Because you like fitness", "https://pbs.twimg.com/profile_images/1230253776793149441/OqV-SeCv.jpg")
        val searchSix = SearchPeople("Marina", "Thoj", "Because you like design", "https://upload.wikimedia.org/wikipedia/en/4/4e/Mount_Mary_University_logo.png")
        val searchEight = SearchPeople("Rick", "Busarow", "Because you like something", "https://pbs.twimg.com/profile_images/1133168921547333632/-_jiPGOu_400x400.png" )
        val searchTen = SearchPeople("Brendan", "Pettis", "Because you like something", "https://pbs.twimg.com/profile_images/1096073042646786050/tLLStoDx.png")
        val searchEleven = SearchPeople("Megan", "Smith", "Because You like design", "https://lh6.googleusercontent.com/-K9ZV4R_Lv14/AAAAAAAAAAI/AAAAAAAAACM/ACYb864hcNc/photo.jpg")

        data.add(searchOne)
        data.add(searchFive)
        data.add(searchSix)
        data.add(searchEight)
        data.add(searchTen)
        data.add(searchEleven)
        return data
    }

    fun searchPeopleClicked() {
        viewState.searchPeopleClicked = true
        searchPeopleNavigationImpl.showOtherUserProfileFragment(bottomNavigationView, viewState.searchPeopleClicked, fragmentManager, containerId, fragment)
    }

    inner class SearchPeopleViewStateImpl: SearchPeopleViewState {
        override val searchPeopleList = returnSampleDataOfPeople()
        override var searchPeopleClicked = false
    }

}