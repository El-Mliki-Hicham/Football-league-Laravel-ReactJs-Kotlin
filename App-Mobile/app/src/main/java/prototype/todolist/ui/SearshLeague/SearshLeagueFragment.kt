package prototype.todolist.ui.SearshLeague

import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import prototype.todolist.R
import prototype.todolist.databinding.FragmentSearshLeagueListBinding
import prototype.todolist.ui.BaseFragment
import prototype.todolist.ui.LeagueViewModel

/**
 * A fragment representing a list of Items.
 */
class SearshLeagueFragment :  BaseFragment<FragmentSearshLeagueListBinding>(FragmentSearshLeagueListBinding::inflate) {

    private  val viewModel: LeagueViewModel by viewModels()
    private lateinit var adapter: SearshLeagueAdapter

    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)
        adapter =  SearshLeagueAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context,2)
            recyclerView.adapter =  adapter
        }

        // getUsers observe
//        viewModel.getListLeague().observe(viewLifecycleOwner, Observer {
//            when (it.status) {
//                Status.LOADING -> this.showProgressBar()
//                Status.ERROR -> this.showResponseError(it.message.toString())
//                Status.SUCCESS -> {
//                    binding.recyclerView.visibility = View.VISIBLE
//                    binding.progressBar.visibility = View.GONE
//                    adapter.apply {
//                        addLeague(it.data!!)
//                        notifyDataSetChanged()
//                    }
//                }
//            }
//        })

    }

    override fun listeners(view: View) {
        binding?.btnSearsh?.setOnClickListener {
            val name = binding?.inputSearsh?.text?.toString()

            if (name != null) {
                viewModel?.SearshLeagues(name)?.observe(viewLifecycleOwner, Observer {
                    binding?.recyclerView?.visibility = View.VISIBLE
                    binding?.progressBar?.visibility = View.GONE
                    adapter?.apply {
                        it.data?.let { league ->
                            addLeague(league)
                            notifyDataSetChanged()
                        }
                    }
                })
            }
        }
    }
}