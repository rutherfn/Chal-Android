package com.nicholasrutherford.chal.account.redesignlogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.databinding.FragmentLoginBinding
import com.nicholasrutherford.chal.ext.fragments.login.LoginFragmentExt
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RedesignLoginFragment @Inject constructor(
    private val typeface: TypefacesImpl,
    private val keyboard: KeyboardImpl
    ) : DaggerFragment(), LoginFragmentExt {

        @Inject
        lateinit var viewmodelFactory: ViewModelProvider.Factory

        private val viewModel by lazy {
            ViewModelProvider(this, viewmodelFactory)
                .get(RedesignLoginViewModel::class.java)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bind = FragmentLoginBinding.inflate(layoutInflater)
        main(bind)
        updateTypefaces(bind)
        textChangedListener(bind)
        editActionListener(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun main(bind: FragmentLoginBinding) {
        lifecycleScope.launch {
            viewModel.loginSuccessState.collect { successState ->
                if (successState) {
                    bind.etEmail.setText("")
                    bind.etPassword.setText("")
                }
            }
        }
    }

    override fun updateTypefaces(bind: FragmentLoginBinding) {
        typeface.setTextViewHeaderBoldTypeface(bind.tvTitle)
        typeface.setTextViewSubHeaderItalicTypeface(bind.tvSubTitle)

        typeface.setTextViewSubHeaderBoldTypeface(bind.tvEmail)
        typeface.setTextViewSubHeaderBoldTypeface(bind.tvPassword)

        typeface.setTextViewSubHeaderRegularTypeface(bind.btLogIn)

        typeface.setTextViewBodyBoldTypeface(bind.tvForgotPassword)
        typeface.setTextViewBodyBoldTypeface(bind.tvSignUp)
    }

    override fun textChangedListener(bind: FragmentLoginBinding) {
        bind.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEmailAfterTextChanged(bind.etEmail.text.toString())

                bind.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorTextVisible
                bind.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorImageVisible
            }
        })

        bind.etPassword.setOnEditorActionListener { _, actionId, _ ->
            viewModel.passwordEditAction(bind.etEmail, bind.etPassword, actionId, bind.etEmail.text.toString())
            false
        }
    }

    override fun editActionListener(bind: FragmentLoginBinding) {
        bind.etEmail.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                keyboard.hideKeyBoard()
            }
            false
        }
    }

    override fun clickListeners(bind: FragmentLoginBinding) {
        bind.btLogIn.setOnClickListener {
            viewModel.onLogInClicked(bind.etEmail, bind.etPassword)
        }
        bind.tvSignUp.setOnClickListener {
            viewModel.onSignUpClicked()
        }
        bind.tvForgotPassword.setOnClickListener {
            viewModel.onForgotPasswordClicked()
        }
    }
}