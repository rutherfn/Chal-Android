package com.nicholasrutherford.chal.fragments.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.navigation.myprofile.MyProfileNavigationImpl
import com.nicholasrutherford.chal.recycler.adapters.MyProfileAdapter
import com.squareup.picasso.Picasso

class MyProfileFragment : Fragment(),
    FragmentExt {

    private var myProfileAdapter: MyProfileAdapter? = null
    private val typeface = Typeface()
    private val helper = Helper()
    private var binding: FragmentProfileBinding? = null
    private var myProfileNavigationImpl = MyProfileNavigationImpl()
    private var btNavigation: BottomNavigationView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation!!
        bind()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
        binding?.tbProfile?.tvTbMyProfileUsername?.let { it -> typeface.setTypefaceForHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvProfileFullName?.let { it -> typeface.setTypefaceForHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvProfileSubInfo?.let { it -> typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.layoutProfile?.tvBodyMessage?.let { typeface.setTypefaceForBodyBold(it, it.context)}

        binding?.layoutProfile?.tvBodyMessage?.text = "Here to have fun on the app by accomplishing fitness challenges, add me to learn more! "

        Picasso.get().load(R.drawable.willplaceholder).into(binding?.layoutProfile?.cvProfilePicture)

        binding?.rvProfile?.isNestedScrollingEnabled = false

        binding?.rvProfile?.layoutManager = GridLayoutManager(context,3)

        val viewModel =
            MyProfileViewModel()
        myProfileAdapter = context?.let { MyProfileAdapter(viewModel, it, this) }
        binding?.rvProfile?.adapter = myProfileAdapter
    }

    override fun updateFragment() {

    }

    override fun clickListeners() {
        binding?.tbProfile?.ibBack?.setOnClickListener {
            fragmentManager?.let { it -> myProfileNavigationImpl.showHomeProfileFragment(true, it, containerId(), btNavigation!!) }
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}