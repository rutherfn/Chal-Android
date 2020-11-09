package com.nicholasrutherford.chal.fragments.debug.debugmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentDebugBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.fragments.changeFontsAndColorsFragment
import com.nicholasrutherford.chal.recycler.adapters.DebugAdapter

class DebugFragment : Fragment(),
    FragmentExt {

    private var debugAdapter: DebugAdapter? = null
    private var binding: FragmentDebugBinding? = null
    private var optionsList: Array<out String>? = null
    private var viewModel: DebugViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDebugBinding.inflate(layoutInflater)
        optionsList = resources.getStringArray(R.array.debugOptions)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
        binding?.rvDebug?.isNestedScrollingEnabled = false
        binding?.rvDebug?.layoutManager = LinearLayoutManager(activity)

        viewModel = context?.let { fragmentManager?.let { it1 ->
            DebugViewModel(
                it, it1, containerId(),
                changeFontsAndColorsFragment, optionsList
            )
        } }

        debugAdapter = context?.let { viewModel?.let { it1 -> DebugAdapter(it1, it) } }
        binding?.rvDebug?.adapter = debugAdapter
    }

    override fun updateFragment() {
        binding?.btnImplementChanges?.text = viewModel?.viewState?.buttonRestartChangesValue
    }

    override fun clickListeners() {

      //  binding?.c
//        binding?.btnImplementChanges?.setOnClickListener { fragmentManager?.let { it -> viewModel?.viewState?.container?.let { it1 ->
//            debugNavigationImpl.showDebugFragment(it,
//                it1, changeFontsAndColorsFragment)
//        } } }
    }

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }
}