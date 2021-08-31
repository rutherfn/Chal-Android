package com.nicholasrutherford.chal.ui.base_vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {

    private val _viewStateUpdated = MutableStateFlow(false)
    val viewStateUpdated: StateFlow<Boolean> = _viewStateUpdated

    init {
        setViewStateAsNotUpdated()
    }

    fun setViewStateAsUpdated() {
        _viewStateUpdated.value = true
    }

    fun setViewStateAsNotUpdated() {
        _viewStateUpdated.value = false
    }
}