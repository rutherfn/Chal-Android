package com.nicholasrutherford.chal.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class ErrorLoginToAccountDialogFragment : DialogFragment() {

    // declarations
    private var mView: View? = null

    private var typeface = Typeface()
    private var helper = Helper()

    private lateinit var ivErrorLoginAccount: ImageView
    private lateinit var tvErrorLoggingIn:  TextView
    private lateinit var btnOk: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.error_login_to_account, container, false)
        isCancelable = false
        main()
        return mView
    }

    private fun main() {
        setupIds()
        setTypefaceAndTextColor()
        okBtnListener()
    }

    private fun setupIds() {
        ivErrorLoginAccount = mView!!.findViewById(R.id.ivErrorLoginAccount)
        tvErrorLoggingIn = mView!!.findViewById(R.id.tvErrorLoggingIn)

        btnOk = mView!!.findViewById(R.id.btnOk)
    }

    private fun setTypefaceAndTextColor() {
        context?.let {

            typeface.setTypefaceForSubHeaderBold(tvErrorLoggingIn, it)
            typeface.setTypefaceForBodyBold(btnOk, it)

            helper.setTextViewColor(it, tvErrorLoggingIn, R.color.colorPrimary)
            helper.setTextViewColor(it, btnOk, R.color.colorBlack)
        }
    }

    private fun okBtnListener() {

        btnOk.setOnClickListener {
            dismiss()
        }
    }

}