package com.nicholasrutherford.chal.account.createaccount

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.databinding.FragmentCreateAccountBinding
import com.nicholasrutherford.chal.ext.CreateAccountFragmentExtension
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateAccountFragment(private val activity: CreateAccountActivity, private val appContext: Context) : Fragment(),
        CreateAccountFragmentExtension {

    private val viewModel = CreateAccountViewModel(activity, appContext)
    private val typeface = Typeface()
    private val helper = Helper()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentCreateAccountBinding.inflate(layoutInflater)
        main(bind)
        updateTypefaces(bind)
        textChangedListener(bind)
        editActionListener(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun main(bind: FragmentCreateAccountBinding) {
        lifecycleScope.launch {
            viewModel.createAccountSuccessState.collect { successState ->
                if(successState) {
                    bind.etUsername.setText("")
                    bind.etEmail.setText("")
                    bind.etPassword.setText("")
                }
            }
        }
    }

    override fun updateTypefaces(bind: FragmentCreateAccountBinding) {
        typeface.setTypefaceForHeaderBold(bind.tvCreate, appContext)
        typeface.setTypefaceForHeaderBold(bind.tvAccount, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.tvUsername, appContext)
        typeface.setTypefaceForBodyBold(bind.tvErrorUsername, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.tvEmail, appContext)
        typeface.setTypefaceForBodyBold(bind.tvErrorEmail, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.tvPassword, appContext)
        typeface.setTypefaceForBodyBold(bind.tvErrorPassword, appContext)
    }

    override fun textChangedListener(bind: FragmentCreateAccountBinding) {
        bind.etEmail.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val email = bind.etEmail.text.toString()
                viewModel.showOrDismissErrorEmail(email)
                bind.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorVisible
                bind.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorVisible
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        bind.etPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val password = bind.etPassword.text.toString()
                viewModel.showOrDismissErrorPassword(password)
                bind.ivErrorPassword.visibleOrGone = viewModel.viewState.passwordErrorVisible
                bind.tvErrorPassword.visibleOrGone = viewModel.viewState.passwordErrorVisible
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        bind.etUsername.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val username = bind.etUsername.text.toString()
                viewModel.showOrDismissErrorUsername(username)
                bind.ivErrorUsername.visibleOrGone = viewModel.viewState.usernameErrorVisible
                bind.tvErrorUsername.visibleOrGone = viewModel.viewState.usernameErrorVisible
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    override fun editActionListener(bind: FragmentCreateAccountBinding) {
        etCloseKeyboardIfUserHitsDone(bind.etEmail)
        etCloseKeyboardIfUserHitsDone(bind.etUsername)
        etCloseKeyboardIfUserHitsDone(bind.etPassword)
    }

    override fun clickListeners(bind: FragmentCreateAccountBinding) {
        val username = bind.etUsername.text.toString()
        val email = bind.etEmail.text.toString()
        val password = bind.etPassword.text.toString()

        bind.btContinueCreating.setOnClickListener {
            viewModel.onClickOnContinue(email, username, password)
        }
    }

    fun etCloseKeyboardIfUserHitsDone(editText: EditText) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(activity)
            }
            false
        }
    }

}