package con.nicholasrutherford.chal.data.challenges

data class JoinableChallenges (
    val id: Int,
    val category: String,
    val categoryNumber: Int,
    val challenges: List<AvailableChallenges>
    )