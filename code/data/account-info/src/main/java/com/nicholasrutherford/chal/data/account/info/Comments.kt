package com.nicholasrutherford.chal.data.account.info

data class Comments(
    val id: Int? = 0,
    val description: String? = "",
    val userName: String? = "",
    val image: String? = "",
    val numberOfLikes: Int? = 0
)