package con.nicholasrutherford.chal.data.challenges

data class CompletedChallenge (
    val name: String,
    val bio: String,
    val categoryName: String,
    var numberOfDaysInChallenge: Int
)