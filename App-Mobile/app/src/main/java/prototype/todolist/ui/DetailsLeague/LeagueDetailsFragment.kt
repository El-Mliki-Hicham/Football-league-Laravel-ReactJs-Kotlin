package prototype.todolist.ui.DetailsLeague
    import android.content.Context
    import android.util.Log
    import android.view.*
    import android.widget.Toast
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.Observer
    import androidx.navigation.findNavController
    import androidx.recyclerview.widget.GridLayoutManager
    import com.squareup.picasso.Picasso
    import prototype.todolist.R
    import prototype.todolist.databinding.FragmentLeagueDtailsListBinding
    import prototype.todolist.models.League
    import prototype.todolist.ui.BaseFragment
    import prototype.todolist.ui.LeagueViewModel
    import prototype.todolist.utils.Status

    class LeagueDetailsFragment : BaseFragment<FragmentLeagueDtailsListBinding>(FragmentLeagueDtailsListBinding::inflate) {
        private var leagueId : Int = 0
        private var leagueName :String = " "
        private var leagueLogo : String = " "
        private var leagueType : String = " "

        private  val viewModel: LeagueViewModel by viewModels()
        private lateinit var adapter: LeagueDetailsAdapter

        override fun init(view: View) {

            arguments?.let {
                leagueId = it.getInt("leagueId", 0)
                Log.d("LeagueListFragment", "League ID: $leagueId")

                 leagueName = it.getString("leagueName", " ")
                Log.d("LeagueListFragment", "League Name: $leagueName")

                leagueLogo = it.getString("leagueLogo", "league Logo")
                Log.d("LeagueListFragment", "League logo: $leagueLogo")
            }

            this.setProgressBar(R.id.progressBar)
            adapter =  LeagueDetailsAdapter(arrayListOf(), view.findNavController() )
            binding.apply {
                recyclerView.layoutManager = GridLayoutManager(context,2)
                recyclerView.adapter =  adapter
                binding.titleLeague.text = leagueName
                Picasso.get().load(leagueLogo).into(binding.logoLeague)
                val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val myString = prefs.getInt("userId", 0)
                Log.d("userId", myString.toString())

                viewModel.CheckFavorite(leagueId,myString).observe(viewLifecycleOwner,Observer{
                    when (it.status) {

                        Status.SUCCESS -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            adapter.apply {
                                binding.btnFavorite.visibility = View.INVISIBLE
                                binding.btnRemoveFavorite.visibility = View.VISIBLE
                            }
                        }
                        else -> { println("league is not in favorite liste") }
                    }

                })
            }

            // getUsers observe
            viewModel.ListTeams(leagueId).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> this.showProgressBar()
                    Status.ERROR -> this.showResponseError(it.message.toString())
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        adapter.apply {
                            addTeam( it.data!!)
                            notifyDataSetChanged()
                        }
                    }
                }
            })

            binding.StandingsBtn.setOnClickListener{

                Log.d("standing", "init: standing list")
            }

        }

        override fun listeners(view: View) {
            binding.apply {
                btnFavorite.setOnClickListener{
                    val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    val myString = prefs.getInt("userId", 0)
                    Log.d("userId", myString.toString())

                    var league = League(
                        leagueId,leagueName,leagueType,leagueLogo,myString
                    )

                    viewModel.AddToFavoriteLeague(league).observe(viewLifecycleOwner, Observer {
                        when (it.status) {
                            Status.LOADING -> showProgressBar()
                            Status.ERROR -> showResponseError(it.message.toString())
                            Status.SUCCESS -> {
                                binding.btnFavorite.visibility = View.INVISIBLE
                                binding.btnRemoveFavorite.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "The league has been added to favorites.", Toast.LENGTH_SHORT).show()

                            }
                        }
                    })

                }


                btnRemoveFavorite.setOnClickListener{

                    val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    val myString = prefs.getInt("userId", 0)
                    Log.d("userId", myString.toString())
                    viewModel.RemoveFavoriteLeague(leagueId,myString).observe(viewLifecycleOwner, Observer {
                        when (it.status) {
                            Status.LOADING -> showProgressBar()
                            Status.ERROR -> showResponseError(it.message.toString())
                            Status.SUCCESS -> {
                                binding.btnFavorite.visibility = View.VISIBLE
                                binding.btnRemoveFavorite.visibility = View.INVISIBLE
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "The league has been removed from favorites.", Toast.LENGTH_SHORT).show()

                            }
                        }
                    })

                }

        //btn standings
                StandingsBtn.setOnClickListener{
                    val action = LeagueDetailsFragmentDirections.actionLeagueDetailsFragmentToStandingsFragment(leagueId, leagueName, leagueLogo)
                    view.findNavController()?.navigate(action)
                }

            }
        }


    }