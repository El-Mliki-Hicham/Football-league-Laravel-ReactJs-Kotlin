package prototype.todolist.models

import com.google.gson.annotations.SerializedName


data class Standing (
    @SerializedName("rank") val rank: Int,
    @SerializedName("team_name") val teamName: String,
    @SerializedName("team_logo") val teamLogo: String,
    @SerializedName("team_id") val teamId: Int,
    @SerializedName("points") val points: Int,
    @SerializedName("played") val played: Int,
    @SerializedName("win") val win: Int,
    @SerializedName("draw") val draw: Int,
    @SerializedName("lose") val lose: Int,
    @SerializedName("for") val goalsFor: Int,
    @SerializedName("against") val goalsAgainst: Int
)