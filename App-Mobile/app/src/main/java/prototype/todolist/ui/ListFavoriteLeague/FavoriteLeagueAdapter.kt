package prototype.todolist.ui.ListFavoriteLeague

import android.content.Context
import prototype.todolist.ui.ListLeague.LeagueListFragmentDirections



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import prototype.todolist.R
import prototype.todolist.models.League
import prototype.todolist.repositoryies.LeagueRepo
import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.ui.ListLeague.LeagueAdapter
import prototype.todolist.utils.Status

class FavoriteLeagueAdapter (private val leagues: ArrayList<League>, navController: NavController )
    : RecyclerView.Adapter<FavoriteLeagueAdapter.DataViewHolder>() {

    private  val repo =LeagueRepo()
    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
         val btnRemove: TextView = view.findViewById<Button>(R.id.btnRemove)
        val Logo : ImageView = view.findViewById(R.id.Logo)
        val cardView: CardView = view.findViewById(R.id.cardviewLeagueItem)
        fun bind(league: League) {
//            NameLeauge.text = league.name
            Picasso.get().load(league.logo).into(Logo)
        }
    }

    // for convert league_items xml to view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_favorite_league, parent, false)
        return DataViewHolder(layout)
    }

    //for determine number of items shuld be displayed
    override fun getItemCount(): Int  = leagues.size



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val league = leagues[position]
        holder.bind(league)
        // btn remove
        holder.btnRemove.setOnClickListener {
            val prefs = holder.itemView.context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val myString = prefs.getInt("userId", 0)
            Log.d("userId", myString.toString())
            CoroutineScope(Dispatchers.Main).launch {


                repo.RemoveFavoriteLeagueRepo(league.id , myString)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        holder.itemView.context,
                        "Deleted from the favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    leagues.removeAt(position) // Remove the task from the list
                    notifyItemRemoved(position)
                }
            }
        }
        //card view click
        holder.cardView.setOnClickListener {
            println(league.name)
            val action = FavoriteLeagueFragmentDirections.actionFavoriteLeagueFragmentToLeagueDetailsFragment(leagueId = league.id,leagueName=league.name,leagueLogo = league.logo)
            navController.navigate(action)
        }
    }

    // this methode  used for clear data in the list and add new data for displaying
    fun addLeague(leagues:List<League>) {
        this.leagues.apply {
            clear()
            addAll(leagues)
        }
    }




}