package com.nicholasrutherford.chal.data.responses

data class ChallengeResponse(
    val id: Int,
    val title: String,
    val body: String,
    val category: String,
    val url: String
)