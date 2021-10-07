package com.nicholasrutherford.chal.main.news.feed

interface NewsFeedViewState {
    var addFriendsEmptyStateVisible: Boolean
    var btnAllTextColor: String
    var btnAllBackgroundResId: Int
    var btnFriendsTextColor: String
    var btnFriendsBackgroundResId: Int
    var btnMyPostsTextColor: String
    var btnMyPostsBackgroundResId: Int
    var isNestedScrollEnabled: Boolean
    var myChallengesVisible: Boolean
    var recyclerNewsFeedVisible: Boolean
    var isEndOfNewsFeedVisible: Boolean

    var bannerVisible: Boolean
    var bannerTitle: String?
    var bannerDescription: String?
    var bannerIsCloseable: Boolean
}