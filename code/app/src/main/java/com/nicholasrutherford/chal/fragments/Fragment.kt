package com.nicholasrutherford.chal.fragments

import com.nicholasrutherford.chal.dialogfragments.*
import com.nicholasrutherford.chal.fragments.debug.changecfontscolors.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.fragments.debug.debugmenu.DebugFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment

// regular fragments
public var challengesFragment = ChallengesFragment()
public var debugFragment =
    DebugFragment()
public var homeFragment = HomeFragment()
public var changeFontsAndColorsFragment =
    ChangeFontsAndColorsFragment()
public var otherUserProfileFragment = OtherUserProfileFragment()
public var myProfileFragment =
    MyProfileFragment()
public var singleChallengeFragment = SingleChallengeFragment()
public var searchPeopleFragment = SearchPeopleFragment()

// alerts fragments
public var debugPasswordDialog = DebugPasswordDialogFragment()
public var errorCreateAccountDialog = ErrorCreateAccountDialogFragment()
public var errorLoginAccountDialog = ErrorLoginToAccountDialogFragment()
public var loadingDialog = LoadingDialogFragment()
public var loadingAccountDialog = SuccessCreateAccountDialogFragment()