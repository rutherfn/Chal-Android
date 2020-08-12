package com.nicholasrutherford.chal.fragments

interface FragmentExt {
    fun bind()
    fun updateFragment()
    fun clickListeners()
    fun containerId(): Int
}