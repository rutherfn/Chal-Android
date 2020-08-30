package com.nicholasrutherford.chal.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.isDayOneChallenge
import com.nicholasrutherford.chal.data.isUserJoinedAChallenge
import com.nicholasrutherford.chal.databinding.FragmentSingleChallengesBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone

// declarations
private val typeface = Typeface()
private val helper = Helper()
private var screenContext: Context? = null
private var binding: FragmentSingleChallengesBinding? = null

class SingleChallengeFragment : Fragment(), FragmentExt {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSingleChallengesBinding.inflate(layoutInflater)
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
        binding?.tbSingleChallenge?.tvTitle?.let { typeface.setTypefaceForHeaderBold(it, it.context ) }
        binding?.tbSingleChallenge?.tvMoreInfo?.let { typeface.setTypefaceForBodyBold(it, it.context) }

        binding?.layoutSingleChallengeFullLayout?.layoutSingleChallenge?.tvTitleOfChallenge?.let { typeface.setTypefaceForSubHeaderBold(it, it.context) }
        binding?.layoutSingleChallengeFullLayout?.layoutSingleChallenge?.tvDescChallenge?.let { typeface.setTypefaceForBodyRegular(it, it.context) }

        binding?.layoutSingleChallengeFullLayout?.layoutSingleChallenge?.button?.let { typeface.setTypefaceForHeaderRegular(it, it.context) }
        binding?.tbSingleChallenge?.tvTitle?.text = "#100DayPushUpChallenge"
    }

    override fun updateFragment() {
    }

    override fun clickListeners() {
        binding?.tbSingleChallenge?.ibBack?.setOnClickListener {
            showChallengesFragment()
        }
        binding?.layoutSingleChallengeFullLayout?.layoutSingleChallenge?.button?.setOnClickListener {
            showAlertDialog("#100DaysPushUpChallenge", "Are you sure you want to join this challenge?")
        }
    }

    private fun showAlertDialog(title: String, body: String) {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle(title)

        builder.setMessage(body)

        builder.setPositiveButton("YES"){dialog, which ->
            joinAChallenge()
        }

        builder.setNegativeButton("NO"){dialog,which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    private fun joinAChallenge() {
        fragmentManager?.let {

            loadingDialog.show(it, "LoadingDialog")

            val timer = object : CountDownTimer(3000, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    loadingDialog.dismiss()
                    isUserJoinedAChallenge = true
                    isDayOneChallenge = true

                    startMyProfileFragment()
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

    private fun showChallengesFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, challengesFragment, challengesFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = true
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

}