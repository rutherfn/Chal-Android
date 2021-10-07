package con.nicholasrutherford.chal.data.challenges

data class ActiveChallenge(
    var name: String,
    var bio: String,
    var categoryName: String,
    var numberOfDaysInChallenge: Int,
    var challengeExpire: String,
    var currentDay: Int,
    var username: String
)