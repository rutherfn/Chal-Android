package com.nicholasrutherford.chal.activitys.accounts//package com.nicholasrutherford.chal.activitys.accounts

//    private fun listeners(binding: ActivityLoginBinding) {
//        binding.btLogIn.setOnClickListener {
//            attemptToSignUserIntoFirebase(binding)
//        }
//
//        binding.tvSignUp.setOnClickListener {
//            startSignUpActivity()
//        }
//
//        binding.tvForgotPassword.setOnClickListener {
//            startForgotPasswordActivity()
//        }
//
//    }
//
//    private fun etEditorActionListeners(binding: ActivityLoginBinding) {
//        binding.etEmail.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                helper.hideSoftKeyBoard(this)
//            }
//            false
//        }
//
//        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
//            val email = binding.etEmail.text.toString()
//            if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
//                attemptToSignUserIntoFirebase(binding)
//            } else if(actionId == EditorInfo.IME_ACTION_DONE) {
//                helper.hideSoftKeyBoard(this)
//            }
//            false
//        }
//
//    }
//
//    private fun showErrorEmail(binding: ActivityLoginBinding) {
//        binding.tvErrorEmail.visibility = View.VISIBLE
//        binding.ivErrorEmail.visibility = View.VISIBLE
//
//        viewModel?.isEmailErrorVisible()
//    }
//
//    private fun dismissErrorEmail(binding: ActivityLoginBinding) {
//        binding.tvErrorEmail.visibility = View.GONE
//        binding.ivErrorEmail.visibility = View.GONE
//
//        viewModel?.isEmailErrorNotVisible()
//    }
//
//    private fun isEmailError(binding: ActivityLoginBinding): Boolean {
//        return binding.ivErrorEmail.visibility == View.VISIBLE && binding.tvErrorEmail.visibility == View.VISIBLE
//    }
//
//    private fun checkIfEmailIsEnteredCorrectly(binding: ActivityLoginBinding) {
//        binding.etEmail.addTextChangedListener (object: TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//                val email = binding.etEmail.text.toString()
//
//                if(email.contains("@") && email.contains(".com")) {
//                    dismissErrorEmail(binding)
//                } else if(email == "") {
//                    dismissErrorEmail(binding)
//                } else {
//                    showErrorEmail(binding)
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })
//
//        }
//
//    private fun attemptToSignUserIntoFirebase(binding: ActivityLoginBinding) {
//
//        helper.hideSoftKeyBoard(this)
//
//        if(isEmailError(binding)) {
//            errorCreateAccountDialog.show(fm, "ErrorLogInAccountDueToFieldsDialog")
//        } else {
//
//            loadingDialog.show(fm, "LoadingDialog")
//
//            val email = binding.etEmail.text.toString()
//            val password = binding.etPassword.text.toString()
//
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if (!it.isSuccessful) return@addOnCompleteListener
//
//                    loadingDialog.dismiss()
//
//                    loadingAccountDialog.show(fm, "LoadingAccountDialog")
//                }
//                .addOnFailureListener {
//
//                    loadingDialog.dismiss()
//
//                    errorLoginAccountDialog.show(fm, "ErrorLoginToAccountDialog")
//
//                    binding.etEmail.text.clear()
//                    binding.etPassword.text.clear()
//                }
//
//        }
//
//        // logic follows for firebase events
//    }
//