package prototype.todolist.models


data class League (
    var id: Int,
    var name: String,
    var type: String,
    var logo: String,
    var user_id: Int?
)