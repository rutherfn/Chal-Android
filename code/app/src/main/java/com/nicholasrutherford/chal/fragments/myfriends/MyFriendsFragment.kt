package com.nicholasrutherford.chal.fragments.myfriends

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentMyFriendsBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.fragments.myProfileFragment
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone

class MyFriendsFragment : Fragment(), FragmentExt {

    private var myFriendsAdapter: MyFriendsAdapter? = null
    private var binding: FragmentMyFriendsBinding? = null
    private var screenContext: Context? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyFriendsBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    override fun bind() {
        setupToolbar()
        setupAdapter()
    }

    fun setupToolbar() {
        binding?.tbMyFriends?.tvTitle?.let {
            typeface.setTypefaceForHeaderBold(it, it.context )
        }
        binding?.tbMyFriends?.tvTitle?.text = "View Friends"
        binding?.tbMyFriends?.tvSubTitle?.text = ""
    }

    fun setupAdapter() {
        myFriendsAdapter = context?.let { MyFriendsAdapter((it)) }
        binding?.rvMyFriends?.isNestedScrollingEnabled = false
        binding?.rvMyFriends?.layoutManager = LinearLayoutManager(activity)
        binding?.rvMyFriends?.adapter = myFriendsAdapter
    }

    override fun updateFragment() {
    }

    override fun clickListeners() {
        binding?.tbMyFriends?.ibBack?.setOnClickListener {
            showMyProfileFragment()
        }
    }

    private fun showMyProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myProfileFragment, myProfileFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}