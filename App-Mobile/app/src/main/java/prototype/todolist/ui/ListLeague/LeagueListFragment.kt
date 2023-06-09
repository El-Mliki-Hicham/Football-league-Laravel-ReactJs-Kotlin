package prototype.todolist.ui.ListLeague

import android.content.Context
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
import prototype.todolist.R
import prototype.todolist.databinding.FragmentLeagueListBinding
import prototype.todolist.ui.BaseFragment
import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.utils.Status

class LeagueListFragment : BaseFragment<FragmentLeagueListBinding>(FragmentLeagueListBinding::inflate) {

    private  val viewModel: LeagueViewModel by viewModels()
    private lateinit var adapter: LeagueAdapter

    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)
        adapter =  LeagueAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context,2)
            recyclerView.adapter =  adapter


        }

        // getUsers observe
        viewModel.getListLeague().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.apply {
                        addLeague(it.data!!)
                        notifyDataSetChanged()
                    }
                }
            }
        })

    }

    override fun listeners(view: View) {
        binding.apply {

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