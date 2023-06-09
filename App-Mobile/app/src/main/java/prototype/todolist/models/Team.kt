package prototype.todolist.models

data class Team(
    val id: Int,
    val name: String,
    val code: String,
    val country: String,
    val founded: Int,
    val national: Int,
    val logo: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val surface: String,
    val image: String,
    val stadium: String,
    val leagueId: Int,
)