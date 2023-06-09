package prototype.todolist.ui.DetailsTeam


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import prototype.todolist.R
import prototype.todolist.databinding.FragmentLeagueListBinding
import prototype.todolist.databinding.FragmentTeamDetailsBinding
import prototype.todolist.ui.BaseFragment
import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.ui.ListLeague.LeagueAdapter
import prototype.todolist.utils.Status

class TeamDetailsFragment : BaseFragment<FragmentTeamDetailsBinding>(FragmentTeamDetailsBinding::inflate) {

    private var teamId : Int = 0
    private var DataName : String = " "
    private var DataLogo : String = " "

    private  val viewModel: LeagueViewModel by viewModels()


    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)

        binding.apply {
            arguments.let{
                if (it != null) {
                    teamId = it.getInt("teamId", 0)
                }

            }
        }

        // getUsers observe
        viewModel.Team(teamId).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
                     DataName= it.data?.name.toString()
                     DataLogo= it.data?.logo.toString()

                    binding.progressBar.visibility = View.GONE
                        binding.nameTeam.text = it.data?.name
                        binding.city.text = it.data?.city
                        binding.adress.text = it.data?.address
                        binding.country.text = it.data?.country
                        binding.stadium.text = it.data?.stadium
                    Picasso.get().load(it.data?.logo).into(binding.teamLogo)
                    Picasso.get().load(it.data?.image).into(binding.imageStade)


                }
                }

        })

    }

    override fun listeners(view: View) {
        binding.apply {

            btnShowPlayers.setOnClickListener{
                val action = TeamDetailsFragmentDirections.actionTeamDetailsFragmentToPlayerFragment(teamId = teamId,teamLogo = DataLogo, teamName = DataName)
                view.findNavController().navigate(action)
            }
        }
    }


    //    // Todo : Implémentez le code du button Ajouter une tâche dans le menu
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.layout_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

}