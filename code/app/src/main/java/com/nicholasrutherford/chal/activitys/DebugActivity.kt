package com.nicholasrutherford.chal.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ActivityDebugBinding
import com.nicholasrutherford.chal.fragments.DebugFragment

class DebugActivity  : AppCompatActivity() {

    private val debugFragment = DebugFragment()

    var binding : ActivityDebugBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        bind()
    }

    fun bind() {
        supportFragmentManager.beginTransaction().replace(R.id.container, debugFragment, debugFragment.javaClass.simpleName)
            .commit()
    }
}