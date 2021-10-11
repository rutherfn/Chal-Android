package con.nicholasrutherford.chal.data.challenges

data class AvailableChallenges (
    val title: String,
    val category: String,
    val url: String,
    val desc: String,
    val time: String,
    val duration: Int,
    val categoryNumber: Int
)