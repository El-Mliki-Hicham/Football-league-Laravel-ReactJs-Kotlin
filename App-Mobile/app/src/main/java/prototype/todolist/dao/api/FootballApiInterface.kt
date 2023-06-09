package prototype.todolist.dao.api

import prototype.todolist.models.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FootballApiInterface {


    @GET("ListLeagues")
  suspend fun getListLeagueInterface() : List<League>



      @GET("ListFavoriteLeague/{id}")
  suspend fun ListFavoriteLeagueInterface(@Path("id") id: Int) : List<League>

    @POST("AddToFavoriteLeague")
  suspend fun AddToFavoriteLeagueInterface(@Body league: League) : League

  @GET("SearshLeagues/{name}")
  suspend fun SearshLeaguesInterface(@Path("name") name : String) :  List<League>

  @POST("Login")
  suspend fun LoginInterface(@Body user: User) :  User

  @GET("ListTeams/{id}")
  suspend fun ListTeamsInterface(@Path("id")  id : Int) : List<Team>

  @GET("PlayersList/{id}")
  suspend fun playersListInterface(@Path("id")  id : Int) : List<Player>

  @GET("Team/{id}")
  suspend fun TeamInterface(@Path("id")  id : Int) : Team

  @DELETE("RemoveFavoriteLeague/{id}/{userId}")
  suspend fun RemoveFavoriteLeagueInterface(@Path("id")  id : Int ,@Path("userId") userId: Int) :  List<League>

  @GET("CheckFavorite/{id}/{userId} ")
  suspend fun CheckFavoriteInterface(@Path("id") id: Int, @Path("userId") userId: Int) : Int

  @GET("CheckFavoriteTeam/{id}")
  suspend fun CheckFavoriteTeamInterface(@Path("id")  id : Int) : Int

  @GET("Standing/{id}")
  suspend fun StandingsInterface(@Path("id")  id : Int) : List<Standing>


  @POST("AddToFavoriteTeam")
  suspend fun AddToFavoriteTeamInterface(@Body id: Int) : Int


}
