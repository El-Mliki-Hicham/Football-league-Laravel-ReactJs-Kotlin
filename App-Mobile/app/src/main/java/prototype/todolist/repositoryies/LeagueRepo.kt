package prototype.todolist.repositoryies

import prototype.todolist.dao.LeagueDao
import prototype.todolist.models.League
import prototype.todolist.models.User

class LeagueRepo {

    private val leagueDao  = LeagueDao()
    suspend fun getListLeagueRepo() = leagueDao.getListLeagueDao()
    suspend fun ListFavoriteLeagueRepo(id: Int) = leagueDao.ListFavoriteLeagueDao(id)
    suspend fun SearshLeaguesRepo(name : String) = leagueDao.SearshLeaguesDao(name)
    suspend fun LoginRepo(user: User) = leagueDao.LoginDao(user)
   // suspend fun AddToFavoriteLeagueRepo(league:League) = leagueDao.AddToFavoriteLeagueDao(league)
    suspend fun ListTeamsRepo(id : Int) = leagueDao.ListTeamsDao(id)
    suspend fun playersListRepo(id : Int) = leagueDao.playersListDao(id)
    suspend fun TeamRepo(id : Int) = leagueDao.TeamDao(id)
    suspend fun CheckFavoriteRepo(id : Int,userId:Int) = leagueDao.CheckFavoriteDao(id,userId)
    suspend fun CheckFavoriteTeamRepo(id : Int) = leagueDao.CheckFavoritTeamDao(id)
    suspend fun RemoveFavoriteLeagueRepo(id : Int , userId: Int) = leagueDao.RemoveFavoriteLeagueDao(id , userId)
    suspend fun StandingsRepo(id : Int) = leagueDao.StandingsDao(id)
    suspend fun AddToFavoriteTeamRepo(id : Int) = leagueDao.AddToFavoriteTeamDao(id)

    suspend fun AddToFavoriteLeagueRepo(league: League){

            // save
            leagueDao.AddToFavoriteLeagueDao(league)
        }
}