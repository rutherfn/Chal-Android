package com.nicholasrutherford.chal.fragments.postprogress

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.isGrateful
import com.nicholasrutherford.chal.databinding.FragmentChallengePostBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.fragments.loadingDialog
import com.nicholasrutherford.chal.fragments.myProfileFragment
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone

class ChallengePostFragment : Fragment(), FragmentExt {
    private var typeface = Typeface()
    private var binding: FragmentChallengePostBinding? = null
    private var screenContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChallengePostBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
        binding?.tbChallengePost?.tvEditProfile?.text = "#10DaysOfPushups"

        binding?.tbChallengePost?.tvEditProfile?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.challengeImageLayout?.tvChallengeImage?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }

        binding?.titleLayout?.tvEtLabel?.text = "Title"
        binding?.titleLayout?.tvEtLabel?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }

        binding?.challengeNameLayout?.tvEtLabel?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.challengeNameLayout?.tvEtLabel?.text = "Challenge Name"
        binding?.challengeNameLayout?.etEmailField?.text =  Editable.Factory.getInstance().newEditable("#100 Days Of Push up Challenge")

        binding?.challengeDayLayout?.tvEtLabel?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.challengeDayLayout?.tvEtLabel?.text = "Day Of Challenge"
        binding?.challengeDayLayout?.etEmailField?.text = Editable.Factory.getInstance().newEditable("Day 1")

        binding?.challengeLengthLayout?.tvEtLabel?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.challengeLengthLayout?.tvEtLabel?.text = "Length Of Challenge"
        binding?.challengeLengthLayout?.etEmailField?.text = Editable.Factory.getInstance().newEditable("7 Days")

        binding?.descMessageLayout?.tvEtLabel?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.descMessageLayout?.tvEtLabel?.text = "Description"
        binding?.descMessageLayout?.etFieldSloganMessage?.text = Editable.Factory.getInstance().newEditable("Just did 10 hard push ups! It was a good first day")


    }

    override fun updateFragment() {
    }

    override fun clickListeners() {
        binding?.tbChallengePost?.ibBack?.setOnClickListener {
            startHomeFragment()
        }
        binding?.tbChallengePost?.ivSave?.setOnClickListener {
            isGrateful = true
                fragmentManager?.let {
                    loadingDialog.show(it, "loadingDialog")
                }

                val timer = object: CountDownTimer(3000, 100) {

                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        startMyProfileFragment()
                        loadingDialog.dismiss()
                    }
                }
                timer.start()
        }
    }

    public fun startMyProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myProfileFragment, myProfileFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun startHomeFragment() {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, myProfileFragment, myProfileFragment::javaClass.name)
                ?.commit()

            if(screenContext != null) {
                (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
            }
    }

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }

}