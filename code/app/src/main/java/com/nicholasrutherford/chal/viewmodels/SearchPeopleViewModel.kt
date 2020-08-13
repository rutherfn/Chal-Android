package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.data.responses.SearchPeople
import com.nicholasrutherford.chal.viewstate.SearchPeopleViewState

class SearchPeopleViewModel(private val context: Context) : ViewModel() {
    val viewState = SearchPeopleViewStateImpl()

    fun returnSampleDataOfPeople(): ArrayList<SearchPeople> {
        val data = ArrayList<SearchPeople>()
        val searchOne = SearchPeople("Nick", "Rutherford",  "Because you like fitness", "https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg")
        val searchTwo = SearchPeople("Shawn", "Spartz", "Because you like meditation", "https://www.cioapplications.com/newstransfer/upload/ai3jmgradient370.jpg")
        val searchThree = SearchPeople("Sami", "Weber", "Because you like reading", "https://media.creativemornings.com/uploads/user/avatar/124117/IMG_0432.JPG" )
        val searchFour = SearchPeople("Kyle", "Wroblesenki", "Because you like something else", "https://pbs.twimg.com/profile_images/917759022530482177/mfp0uWCQ.jpg")
        val searchFive = SearchPeople("Patrick", "McGinn", "Because you like fitness", "https://pbs.twimg.com/profile_images/1230253776793149441/OqV-SeCv.jpg")
        val searchSix = SearchPeople("Marina", "Thoj", "Because you like design", "https://upload.wikimedia.org/wikipedia/en/4/4e/Mount_Mary_University_logo.png")
        val searchEight = SearchPeople("Rick", "Busarow", "Because you like something", "https://pbs.twimg.com/profile_images/1133168921547333632/-_jiPGOu_400x400.png" )
        val searchNine = SearchPeople("Jacobo", "Hernandez", "Because you like reading", "https://insights.dice.com/wp-content/uploads/2019/11/shutterstock_1294805476.jpg")
        val searchTen = SearchPeople("Brendan", "Pettis", "Because you like something", "https://pbs.twimg.com/profile_images/1096073042646786050/tLLStoDx.png")
        val searchEleven = SearchPeople("Megan", "Heller", "Because You like design", "https://lh6.googleusercontent.com/-K9ZV4R_Lv14/AAAAAAAAAAI/AAAAAAAAACM/ACYb864hcNc/photo.jpg")

        data.add(searchOne)
        data.add(searchTwo)
        data.add(searchThree)
        data.add(searchFour)
        data.add(searchFive)
        data.add(searchSix)
        data.add(searchEight)
        data.add(searchNine)
        data.add(searchTen)
        data.add(searchEleven)
        return data
    }

    inner class SearchPeopleViewStateImpl: SearchPeopleViewState {
        override val searchPeopleList = returnSampleDataOfPeople()
    }

}