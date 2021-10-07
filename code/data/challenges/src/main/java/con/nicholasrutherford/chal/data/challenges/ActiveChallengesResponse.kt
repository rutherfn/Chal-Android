package con.nicholasrutherford.chal.data.challenges

data class ActiveChallengesResponse (
    val bio: String = "",
    val categoryName: String = "",
    val currentDay: Int = 0,
    val dateChallengeExpired: String = "",
    val name: String = "",
    val numberOfDaysOfChallenge: Int = 0,
    val username: String = ""
)