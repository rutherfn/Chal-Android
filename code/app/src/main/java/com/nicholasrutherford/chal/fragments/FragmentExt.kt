package com.nicholasrutherford.chal.fragments

interface FragmentExt {
    fun bind()
    fun updateFragment()
    fun updateTypefaces()
    fun updateColors()
    fun clickListeners()
    fun containerId(): Int
}