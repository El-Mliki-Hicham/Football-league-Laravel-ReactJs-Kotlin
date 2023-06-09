package prototype.todolist.dao

import prototype.todolist.dao.api.FootballApiInterface
import prototype.todolist.models.League
import prototype.todolist.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeagueDao {


    companion object{

     //   private var BASE_URL = "http://192.168.1.115:8000/api/"
     private var BASE_URL = "http://192.168.2.53:8000/api/"

        // it a Gson library use  it for get api and convert json to object
        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
        }
        //specify the interface that should be used to define the API
        val apiService: FootballApiInterface = getRetrofit().create(FootballApiInterface::class.java)
    }

    suspend fun getListLeagueDao() = apiService.getListLeagueInterface()
    suspend fun ListFavoriteLeagueDao(id: Int) = apiService.ListFavoriteLeagueInterface(id)
    suspend fun SearshLeaguesDao(name : String) = apiService.SearshLeaguesInterface(name)
    suspend fun LoginDao(user: User) = apiService.LoginInterface(user)
    suspend fun AddToFavoriteLeagueDao(league: League) = apiService.AddToFavoriteLeagueInterface(league)
    suspend fun ListTeamsDao(id : Int) = apiService.ListTeamsInterface(id)
    suspend fun playersListDao(id : Int) = apiService.playersListInterface(id)
    suspend fun TeamDao(id : Int) = apiService.TeamInterface(id)
    suspend fun CheckFavoriteDao(id : Int,userId : Int) = apiService.CheckFavoriteInterface(id,userId)
    suspend fun CheckFavoritTeamDao(id : Int) = apiService.CheckFavoriteTeamInterface(id)
    suspend fun RemoveFavoriteLeagueDao(id : Int, userId: Int) = apiService.RemoveFavoriteLeagueInterface(id ,userId)
    suspend fun StandingsDao(id : Int) = apiService.StandingsInterface(id)
    suspend fun AddToFavoriteTeamDao(id : Int) = apiService.AddToFavoriteTeamInterface(id)






}