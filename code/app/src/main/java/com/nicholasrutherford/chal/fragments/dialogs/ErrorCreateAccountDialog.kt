package com.nicholasrutherford.chal.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.accounts.CreateAccountActivity
import com.nicholasrutherford.chal.activitys.accounts.UploadPhotoActivity
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class ErrorCreateAccountDialog : DialogFragment() {

    // declarations
    private var mView: View? = null

    private var typeface = Typeface()
    private var helper = Helper()

    private lateinit var ivErrorCreateAccount: ImageView
    private lateinit var tvErrorCreating: TextView
    private lateinit var btnOk: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.error_create_account_dialog, container, false)
        isCancelable = false
        main()
        return mView
    }

    private fun main() {
        setupIds()
        setTypefaceAndTextColor()
        okBtnListener()
        setTextForActivityDialogAlert()
    }

    private fun setupIds() {
        ivErrorCreateAccount = mView!!.findViewById(R.id.ivErrorCreateAccount)
        tvErrorCreating = mView!!.findViewById(R.id.tvErrorCreating)

        btnOk = mView!!.findViewById(R.id.btnOk)
    }

    private fun setTypefaceAndTextColor() {
        context?.let {

            typeface.setTypefaceForSubHeaderBold(tvErrorCreating, it)
            typeface.setTypefaceForBodyBold(btnOk, it)

            helper.setTextViewColor(it, tvErrorCreating, R.color.colorPrimary)
            helper.setTextViewColor(it, btnOk, R.color.colorBlack)
        }
    }

    private fun setTextForActivityDialogAlert() {
        if(activity == CreateAccountActivity::class.java) {
            tvErrorCreating.text = getText(R.string.error_creating_create_account_fields)
        }
        if(activity == UploadPhotoActivity::class.java) {
            // could be multiple reasons why firebase does not work, find a way to get why
            tvErrorCreating.text = getText(R.string.error_creating_account_firebase)
        }
    }

    private fun okBtnListener() {

        btnOk.setOnClickListener {
            dismiss()
        }
    }

}
