package com.nicholasrutherford.chal

import javax.inject.Inject

@Suppress("MagicNumber")
const val COMMAND_PING_GOOGLE = "ping -c 1 google.com"
const val WAIT_FOR_VALUE = 0

class Networkimpl @Inject constructor() : Network {

    override fun isConnected(): Boolean {
        val command = COMMAND_PING_GOOGLE
        return Runtime.getRuntime().exec(command).waitFor() == WAIT_FOR_VALUE
    }
}