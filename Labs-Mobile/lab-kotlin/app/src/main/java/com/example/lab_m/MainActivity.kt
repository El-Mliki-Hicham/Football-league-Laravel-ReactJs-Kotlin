package com.example.lab_m

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//appComonentActivity : pour access to the android framworks and ui compoantes exemple recycleView , Material design.
//recycleView : use  for display data in list view
//Material design use it for create a user interface  for view with ux
class MainActivity : AppCompatActivity() {
    //oncreate method is a lifecycle method called when the  activity is ctreated
    // onCreate uses to set up activity and initialize any var or oblejct that the activity needs,
    //lyfecycle it  use for update the state of activity
    // Bundle is a data structure used to pass data betwen componentes of app
    //savedInstanceState use for save and restor state of activity
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.oncreate() called the parent method oncreate
        super.onCreate(savedInstanceState)
        //set the layout file for the activity
        setContentView(R.layout.activity_main)
    }
}