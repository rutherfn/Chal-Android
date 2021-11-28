package com.nicholasrutherford.chal.challenge.manager

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.chal.challenge.manager.databinding.ActivityChallengeManagerBinding
import com.nicholasrutherford.chal.main.navigation.Navigator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeManagerActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private var binding: ActivityChallengeManagerBinding? = null

    private lateinit var navChallengeManagerHostFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChallengeManagerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        navChallengeManagerHostFragment =
            requireNotNull(supportFragmentManager.findFragmentById(R.id.container))

        navigator.navController = navChallengeManagerHostFragment.findNavController()
    }
}