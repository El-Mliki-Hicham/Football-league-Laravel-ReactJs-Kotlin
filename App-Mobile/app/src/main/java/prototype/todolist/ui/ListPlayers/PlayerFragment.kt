package prototype.todolist.ui.ListPlayers

import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import prototype.todolist.R

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager


import com.squareup.picasso.Picasso
import prototype.todolist.databinding.FragmentPlayersListBinding


import prototype.todolist.databinding.FragmentStandingListBinding
import prototype.todolist.models.League
import prototype.todolist.models.Team
import prototype.todolist.ui.BaseFragment


import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.ui.SearshLeague.SearshLeagueFragmentDirections
import prototype.todolist.ui.StandingsLeagues.StandingsAdapter
import prototype.todolist.ui.StandingsLeagues.standingsFragmentDirections


import prototype.todolist.utils.Status

class PlayerFragment : BaseFragment<FragmentPlayersListBinding>(FragmentPlayersListBinding::inflate) {
    private var teamId : Int = 0
    private var teamName :String = " "
    private var teamLogo : String = " "


    private  val viewModel: LeagueViewModel by viewModels()
    private lateinit var adapter: PlayersAdapter

    override fun init(view: View) {

        arguments?.let {
            teamId = it.getInt("teamId", 0)
            Log.d("dddd", "init: $teamId")

            teamName = it.getString("teamName", " ")
            Log.d("dddd", "init: $teamName")

            teamLogo = it.getString("teamLogo", " ")

        }

        this.setProgressBar(R.id.progressBar)
        adapter =  PlayersAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
         recyclerView.layoutManager = GridLayoutManager(context,2)
          recyclerView.adapter =  adapter
            binding.titleTeam.text = teamName
            Picasso.get().load(teamLogo).into(binding.TeamLogo)

            viewModel.CheckFavoriteTeam(teamId).observe(viewLifecycleOwner,Observer{
                when (it.status) {

                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        adapter.apply {
                            binding.btnFavorite.visibility = View.INVISIBLE
                            binding.btnRemoveFavorite.visibility = View.VISIBLE
                        }
                    }
                    else -> { println("team is not in favorite liste") }
                }

            })
        }

        // getUsers observe
        viewModel.playersList(teamId).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.apply {
                        addPlayer( it.data!!)
                        notifyDataSetChanged()
                    }
                }
            }
        })

    }

    override fun listeners(view: View) {
        binding.apply {
//            btnFavorite.setOnClickListener{
//                viewModel.AddToFavoriteTeam(teamId).observe(viewLifecycleOwner, Observer {
//                    when (it.status) {
//
//                        Status.LOADING -> showProgressBar()
//                        Status.ERROR -> showResponseError(it.message.toString())
//                        Status.SUCCESS -> {
//
//                            binding.btnFavorite.visibility = View.INVISIBLE
//                            binding.btnRemoveFavorite.visibility = View.VISIBLE
//                            binding.progressBar.visibility = View.GONE
//                            Toast.makeText(context, "The league has been added to favorites.", Toast.LENGTH_SHORT).show()
//
//                        }
//                    }
//                })

//            }
//
//
//            btnRemoveFavorite.setOnClickListener{
//                viewModel.RemoveFavoriteLeague(leagueId).observe(viewLifecycleOwner, Observer {
//                    when (it.status) {
//                        Status.LOADING -> showProgressBar()
//                        Status.ERROR -> showResponseError(it.message.toString())
//                        Status.SUCCESS -> {
//                            binding.btnFavorite.visibility = View.VISIBLE
//                            binding.btnRemoveFavorite.visibility = View.INVISIBLE
//                            binding.progressBar.visibility = View.GONE
//                            Toast.makeText(context, "The league has been removed from favorites.", Toast.LENGTH_SHORT).show()
//
//                        }
//                    }
//                })
//
//            }
//
//            // btn listTeams
//            listTeams.setOnClickListener{
//                val action = standingsFragmentDirections.actionStandingsFragmentToLeagueDetailsFragment(leagueId, leagueName, leagueLogo)
//                view.findNavController().navigate(action)
//            }


        }
    }


}