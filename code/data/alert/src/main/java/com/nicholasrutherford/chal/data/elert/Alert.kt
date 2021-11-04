package com.nicholasrutherford.chal.data.elert

data class Alert (
    val title: String?,
    val message: String?,
    val type: AlertType?,
    val shouldCloseAppAfterDone: Boolean
    )