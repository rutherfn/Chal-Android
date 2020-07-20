package com.nicholasrutherford.chal.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.LoginActivity
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class SuccessCreateAccountDialog : DialogFragment() {

    // declarations
    private var mView: View? = null

    private var typeface = Typeface()
    private var helper = Helper()

    private lateinit var tvSuccessCreating: TextView
    private lateinit var tvLoadingYourAccount: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.success_create_account_dialog, container, false)
        isCancelable = false
        main()
        return mView
    }

    private fun main() {
        setupIds()
        setTypefaceAndTextColor()
        countdownTimerForNewActivity()
    }

    private fun setupIds() {
        tvSuccessCreating = mView!!.findViewById(R.id.tvSuccessCreating)
        tvLoadingYourAccount = mView!!.findViewById(R.id.tvLoadingYourAccount)
    }

    private fun setTypefaceAndTextColor() {
        context?.let {

            typeface.setTypefaceForHeaderBold(tvSuccessCreating, it)
            typeface.setTypefaceForSubHeaderBold(tvLoadingYourAccount, it)

            helper.setTextViewColor(it, tvSuccessCreating, R.color.colorGreen)
            helper.setTextViewColor(it, tvLoadingYourAccount, R.color.colorBlack)
        }
    }

    private fun startOnBoardActivity() {
        context?.let {
            val intent = Intent(it.applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun countdownTimerForNewActivity() {
        val timer = object: CountDownTimer(7000, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() { startOnBoardActivity()}
        }
        timer.start()

    }
}