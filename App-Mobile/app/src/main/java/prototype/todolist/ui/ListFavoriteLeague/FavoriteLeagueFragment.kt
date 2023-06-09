package prototype.todolist.ui.ListFavoriteLeague

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
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
import prototype.todolist.databinding.FragmentFavoriteLeagueListBinding
import prototype.todolist.databinding.FragmentLeagueListBinding
import prototype.todolist.ui.BaseFragment
import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.ui.ListLeague.LeagueAdapter
import prototype.todolist.utils.Status

class FavoriteLeagueFragment : BaseFragment<FragmentFavoriteLeagueListBinding>(FragmentFavoriteLeagueListBinding::inflate) {


    // To retrieve a string value

    private  val viewModel: LeagueViewModel by viewModels()
    private lateinit var adapter: FavoriteLeagueAdapter

    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)
        adapter =  FavoriteLeagueAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context,2)
            recyclerView.adapter =  adapter



        }
        val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val myString = prefs.getInt("userId", 0)
        Log.d("userId", myString.toString())
        // getUsers observe
        viewModel.GetFavoriteLeague(myString).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
        Log.d("ddd", it?.data.toString())

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