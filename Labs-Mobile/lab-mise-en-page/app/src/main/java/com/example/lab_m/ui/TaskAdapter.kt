package com.example.lab_m.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_m.R
import com.example.lab_m.data.TaskRepo
import java.text.SimpleDateFormat
import java.util.*

//adapter extend the RecyclerView.Adapter
class TaskAdapter() :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    var taskRepo = TaskRepo()
    //TaskViewHolder method used for  declare content of items
    class TaskViewHolder (private val viewItem: View):RecyclerView.ViewHolder(viewItem) {
        val title: TextView = viewItem.findViewById<Button>(R.id.title)
        val priority: TextView = viewItem.findViewById<Button>(R.id.priorite)
        val taskTimestamp: TextView = viewItem.findViewById<Button>(R.id.time)
        val cardView: CardView = viewItem.findViewById(R.id.cardview)
    }

    // params->parent : is the view group when the viewHolder will be add to
    //params->viewType : type of view to be created :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //layoutInflater use to inflate the layout for the items
        //inflate method return new view object that represent the inflate  layout
        var layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list,parent,false)
        return TaskViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val context = holder.itemView.context // Get the context from the itemView
       val prioritie = context.resources.getStringArray(R.array.priorities)
        val task = this.taskRepo.getAll()[position]
        val prioritiesIndex = task.Priority - 1
        holder.title.text = task.Task

        holder.priority.text = prioritie[prioritiesIndex]
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        holder.taskTimestamp.text = dateFormat.format(task.TimeTemps)

        holder.cardView.setOnClickListener{
            task.Task = task.Task + "+"
            //for  auto change data
            this.notifyDataSetChanged()
            Toast.makeText(context,"Update $task", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        println(taskRepo.getAll().size)
          return  taskRepo.getAll().size
    }


}
