package prototype.todolist.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


typealias Inflater<T> = (inflater: LayoutInflater, view: ViewGroup?, attach: Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(private val inflater: Inflater<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var progressBar: ProgressBar? = null


    // for inflate layout fragment and create view for  fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
//used to find views in the fragment's view and set up event listeners
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        listeners(view)


    }
    //for events
    abstract fun listeners(view: View)
// for find view
    abstract fun init(view: View)

    protected open fun observers() {}


    fun setProgressBar(resId: Int) {
        progressBar = view?.findViewById(resId)
    }

    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun showResponseError(message : String){
        this.hideProgressBar()
        Log.d("tasks",message )
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}


//
//typealias Inflater<T> = (inflater: LayoutInflater, view: ViewGroup?, attach: Boolean) -> T
//
//abstract class BaseFragment<VBinding : ViewBinding, VModel : ViewModel>(private val inflater: Inflater<VB>) : Fragment() {
//

//
//
////    protected lateinit var viewModel: VModel
////    protected abstract fun getViewModelClass(): Class<ViewModel>
//
//    protected lateinit var binding: VBinding
//    protected abstract fun getViewBinding(): VBinding
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setUpViews(view)
//        observeData()
//    }
//
//    open fun setUpViews(view: View) {}
//
//    open fun observeView() {}
//
//    open fun observeData() {}
//
//    private fun init() {
//        binding = getViewBinding()
////        viewModel = ViewModelProvider(this).get(getViewModelClass()) as VModel
//    }

//}