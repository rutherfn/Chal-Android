package com.nicholasrutherford.chal.helpers

import android.view.View

var View.visibleOrGone: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }