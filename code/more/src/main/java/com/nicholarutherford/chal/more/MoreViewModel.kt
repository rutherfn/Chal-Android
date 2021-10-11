package com.nicholarutherford.chal.more

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class MoreViewModel @ViewModelInject constructor(
    private val application: Application
) : BaseViewModel() {
}