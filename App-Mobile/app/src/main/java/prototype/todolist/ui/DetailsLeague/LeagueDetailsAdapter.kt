package prototype.todolist.ui.DetailsLeague

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import prototype.todolist.R
import prototype.todolist.models.Team
import prototype.todolist.ui.DetailsTeam.TeamDetailsFragmentDirections
import prototype.todolist.ui.ListLeague.LeagueListFragmentDirections

class LeagueDetailsAdapter (private val teams: ArrayList<Team>, navController: NavController)
    : RecyclerView.Adapter<LeagueDetailsAdapter.DataViewHolder>() {

    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val Logo : ImageView = view.findViewById(R.id.Logo)
        val cardView: CardView = view.findViewById(R.id.cardviewLeagueItem)

        fun bind(team: Team) {
//            NameLeauge.text = league.name
            Picasso.get().load(team.logo).into(Logo)
        }
    }

    // for convert league_items xml to view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.league_item, parent, false)
        return DataViewHolder(layout)
    }

    //for determine number of items shuld be displayed
    override fun getItemCount(): Int  = teams.size



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val teams = teams[position]
        holder.bind(teams)
        holder.cardView.setOnClickListener {
            val action = LeagueDetailsFragmentDirections.actionLeagueDetailsFragmentToTeamDetailsFragment(teamId = teams.id)
            navController.navigate(action)
        }

    }

    // this methode  used for clear data in the list and add new data for displaying
    fun addTeam(teams:List<Team>) {
        this.teams.apply {
            clear()
            addAll(teams)
        }
    }




}