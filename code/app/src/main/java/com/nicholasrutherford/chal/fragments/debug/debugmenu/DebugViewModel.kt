package com.nicholasrutherford.chal.fragments.debug.debugmenu

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.debug.Debug
import com.nicholasrutherford.chal.fragments.debug.changecfontscolors.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.navigation.debug.DebugNavigationImpl

class DebugViewModel(private val context: Context, private val fragmentManager: FragmentManager, private val containerId: Int, private val fragment: ChangeFontsAndColorsFragment, private val listOfOptions: Array<out String>?) : ViewModel() {

    // declarations
    val viewState = DebugViewStateImpl()
    private val debugNavigationImpl = DebugNavigationImpl()

    init {
        debugOptions()
    }

    fun changeConfigurationsClicked(value: String, i: Int ) {
        viewState.isChangeConfigurationsClicked = true
        println(context.getString(value.toInt()))
       // println(viewState.debugOptionsList[i].options)
        if(context.getString(value.toInt()) == viewState.debugOptionsList[i].options) {
            debugNavigationImpl.showChangeFontsAndColorsFragment(viewState.isChangeConfigurationsClicked, fragmentManager, containerId, fragment)
        } else {
        }
    }

    private fun debugOptions(): MutableList<Debug>? {
        if (listOfOptions != null) {
            for (i in listOfOptions) {
                val debugOptions = Debug(i)
                viewState.debugOptionsList.add(debugOptions)
            }
        }
        return viewState.debugOptionsList
    }

    inner class DebugViewStateImpl() :
        DebugViewState {
        override var isChangeConfigurationsClicked = false
        override val container = R.id.container
        override val buttonRestartChangesValue = "Restart"
        override val debugModeVisible = false
        override val debugOptionsList = ArrayList<Debug>()
    }

}