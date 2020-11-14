package com.nicholasrutherford.chal.account

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentLoginBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.visibleOrGone

class LoginFragment(activity: LoginActivity, private val appContext: Context) : Fragment(), LoginFragmentExt {
    private var viewModel = LoginViewModel(activity, appContext)
    private var helper = Helper()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentLoginBinding.inflate(layoutInflater)
        bind(bind)
        updateView(bind)
        textChangedListener(bind)
        editActionListener(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun bind(bind: FragmentLoginBinding) {

    }

    override fun updateView(bind: FragmentLoginBinding) {
        updateTypefaces(bind)
        updateColors(bind)
    }

    override fun updateTypefaces(bind: FragmentLoginBinding) {
        viewModel.headerBold(bind.tvTitle, appContext)
        viewModel.headerBold(bind.btLogIn, appContext)

        viewModel.subHeaderBold(bind.tvErrorEmail, appContext)

        viewModel.bodyBold(bind.tvEmail, appContext)
        viewModel.bodyBold(bind.tvPassword, appContext)
        viewModel.bodyBold(bind.tvSignUp, appContext)

        viewModel.bodyItalic(bind.tvForgotPassword, appContext)
        viewModel.lightBody(bind.tvDoNotHaveAccount, appContext)
    }

    override fun updateColors(bind: FragmentLoginBinding) {
        helper.setTextViewColor(appContext, bind.tvTitle, R.color.colorPrimary)
        helper.setTextViewColor(appContext, bind.tvSubTitle, R.color.colorBlack)

        helper.setTextViewColor(appContext, bind.tvEmail, R.color.colorPrimary)
        helper.setTextViewColor(appContext, bind.tvPassword, R.color.colorPrimary)

        helper.setTextViewColor(appContext, bind.btLogIn, R.color.colorSmokeWhite)
        helper.setTextViewColor(appContext, bind.tvForgotPassword, R.color.colorBlue)
        helper.setTextViewColor(appContext, bind.tvDoNotHaveAccount, R.color.colorBlack)

        helper.setTextViewColor(appContext, bind.tvSignUp, R.color.colorBlue)
        helper.setTextViewColor(appContext, bind.tvErrorEmail, R.color.colorBlack)
    }

    override fun textChangedListener(bind: FragmentLoginBinding) {
        bind.etEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEmailAfterTextChanged(bind.etEmail.text.toString())

                bind.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorTextVisible
                bind.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorImageVisible
            }

        })
    }

    override fun editActionListener(bind: FragmentLoginBinding) {
//        bind.etEmail.setOnEditorActionListener { _, actionId, _ ->
//          if (actionId == EditorInfo.IME_ACTION_DONE) {
//              helper.hideSoftKeyBoard(activity)
//          }
//            false
//        }


    }

    override fun clickListeners(bind: FragmentLoginBinding) {
        bind.btLogIn.setOnClickListener {
            // functionality for login
        }

        bind.tvSignUp.setOnClickListener {
            viewModel.onSignUpClicked()
        }

        bind.tvForgotPassword.setOnClickListener {
            viewModel.onForgotPasswordClicked()
        }
    }

}