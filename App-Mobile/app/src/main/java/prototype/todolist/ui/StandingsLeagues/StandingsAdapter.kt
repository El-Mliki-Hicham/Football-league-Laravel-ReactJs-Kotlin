package prototype.todolist.ui.StandingsLeagues


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import prototype.todolist.R
import prototype.todolist.models.Standing

class StandingsAdapter (private val standings: ArrayList<Standing>, navController: NavController)
    : RecyclerView.Adapter<StandingsAdapter.DataViewHolder>() {

    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val rank : TextView = view.findViewById<Button>(R.id.rank)
        val teamLogo: ImageView = view.findViewById(R.id.teamLogo)
        val win: TextView = view.findViewById<Button>(R.id.win)
        val draw: TextView = view.findViewById<Button>(R.id.draw)
        val lose: TextView = view.findViewById<Button>(R.id.lose)
        val  goals: TextView = view.findViewById<Button>(R.id.goals)
        val  pts: TextView = view.findViewById<Button>(R.id.pts)



        fun bind(standing: Standing) {
              rank.text = standing.rank.toString()
              win.text = standing.win.toString()
              draw.text = standing.draw.toString()
              lose.text = standing.lose.toString()
              goals.text = standing.goalsFor.toString()
              pts.text = standing.points.toString()
            Picasso.get().load(standing.teamLogo).into(teamLogo)
        }
    }

    // for convert league_items xml to view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_standings, parent, false)
        return DataViewHolder(layout)
    }

    //for determine number of items shuld be displayed
    override fun getItemCount(): Int  = standings.size



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val standing = standings[position]
        holder.bind(standing)

    }

    // this methode  used for clear data in the list and add new data for displaying
    fun addStanding(standing:List<Standing>) {
        this.standings.apply {
            clear()
            addAll(standing)
        }
    }



}