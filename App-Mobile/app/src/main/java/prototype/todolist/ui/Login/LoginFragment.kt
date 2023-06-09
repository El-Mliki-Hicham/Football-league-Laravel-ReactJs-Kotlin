package prototype.todolist.ui.Login

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import prototype.todolist.R
import prototype.todolist.databinding.FragmentLoginBinding
import prototype.todolist.models.User
import prototype.todolist.ui.BaseFragment
import prototype.todolist.ui.LeagueViewModel
import prototype.todolist.utils.Status


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private var userId : Int = 0
    private var DataName : String = " "
    private var DataLogo : String = " "


    // To save a string value



    private  val viewModel: LeagueViewModel by viewModels()


    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)

        binding.apply {

        }

        // getUsers observe
//        viewModel.Login(user).observe(viewLifecycleOwner, Observer {
//            when (it.status) {
//                Status.LOADING -> this.showProgressBar()
//                Status.ERROR -> this.showResponseError(it.message.toString())
//                Status.SUCCESS -> {
//
//
//
//
//                }
//            }
//
//        })

    }

    override fun listeners(view: View) {
        binding.apply {

            loginBtn.setOnClickListener{
                 val email= email.text
                 val password= password.text
                var user = User(
                    userId,
                    name="hicham",
                    email=email.toString(),
                    password=password.toString()
                )

                           // Log.d("ddddddddddddd", "listeners: $user")

                viewModel.Login(user).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> showProgressBar()
                        Status.ERROR ->  showResponseError(it.message.toString())
                        Status.ERROR -> Toast.makeText(context, "Email or password is incorrect", Toast.LENGTH_SHORT).show()
                        Status.SUCCESS -> {
                          // Log.d("ddddddddddddd", "listeners: ${it.data}")
                            val idUser = it.data?.id
                            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                            if (idUser != null) {
                                prefs.edit().putInt("userId", idUser).apply()
                            }
                            binding.email.setText("")
                            binding.password.setText("")


               val action = LoginFragmentDirections.actionLoginFragmentToLeagueListFragment()
                view.findNavController().navigate(action)


                        }
                    }

                })




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