package prototype.todolist.ui.ListPlayers

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
import prototype.todolist.models.Player
import prototype.todolist.models.Standing

class PlayersAdapter (private val players: ArrayList<Player>, navController: NavController)
    : RecyclerView.Adapter<PlayersAdapter.DataViewHolder>() {

    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val playerImage: ImageView = view.findViewById(R.id.PlayerImage)



        fun bind(player: Player) {

            Picasso.get().load(player.photo).into(playerImage)
        }
    }

    // for convert league_items xml to view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_players, parent, false)
        return DataViewHolder(layout)
    }

    //for determine number of items shuld be displayed
    override fun getItemCount(): Int  = players.size



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)

    }

    // this methode  used for clear data in the list and add new data for displaying
    fun addPlayer(players:List<Player>) {
        this.players.apply {
            clear()
            addAll(players)
        }
    }



}