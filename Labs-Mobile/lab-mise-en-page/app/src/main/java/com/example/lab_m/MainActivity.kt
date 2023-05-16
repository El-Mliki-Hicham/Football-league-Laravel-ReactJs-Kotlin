package com.example.lab_m


import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_m.databinding.ActivityMainBinding
import com.example.lab_m.ui.TaskAdapter

//appComonentActivity : pour access to the android framworks and ui compoantes exemple recycleView , Material design.
//recycleView : use  for display data in list view
//Material design use it for create a user interface  for view with ux
class MainActivity : AppCompatActivity() {
    //oncreate method is a lifecycle method called when the  activity is ctreated
    // onCreate uses to set up activity and initialize any var or oblejct that the activity needs,
    //lyfecycle it  use for update the state of activity
    // Bundle is a data structure used to pass data betwen componentes of app
    //savedInstanceState use for save and restor state of activity
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.oncreate() called the parent method oncreate
        super.onCreate(savedInstanceState)
        //data binding used to bind layout file with data source
        //inflate() methode used it for convert xml to view object can be displayed in the ui .
     val binding =ActivityMainBinding.inflate(layoutInflater)
        //set the layout file for the activity
        setContentView(binding.root)

        //binding.apply used to access views in a layout file using view binding
        binding.apply {
            var taskAdapter = TaskAdapter()
        //layoutManager is respons for  positioning the items in the recycle view , example we use linearLayoutManager in activity_main.xml
            recyclerViewRef.layoutManager  = LinearLayoutManager(applicationContext)
            recyclerViewRef.adapter = taskAdapter

        }


    }
}