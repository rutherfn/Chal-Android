package com.nicholasrutherford.chal.challenge.detail

interface ChallengeDetailViewState {
    val title: String
    val description: String
    val category: String
    val categoryIcon: Int?
    val challengeUrlImage: String
}