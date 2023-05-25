package com.example.lab_m.data

import com.example.lab_m.data.TaskDAO.Companion.listData
import java.nio.file.Files.find

class TaskDAO {

    companion object{
       private var count = 0

        private var listData :MutableList<task> = mutableListOf<task>()
init {
    for (i in 1..10){
        var task1 =task(id = ++ count, Task = "hicham $i", Priority = 2,System.currentTimeMillis())
        listData.add(task1)
    }


    }
 var  repo = TaskRepo()
    }
    fun insert(task: task): Any {
        return listData.add(0,task)
    }



    fun update(task: task) {

        var index = this.findIndexById(task.id)
            TaskDAO.listData[index-1] = task
    }
    fun findIndexById (id:Int): Int {
        val oneTask =  TaskDAO.listData.find { it.id == id}
        if (oneTask != null) {
          var  id =  oneTask.id

        }
        return id
    }

    fun delete(id:Int) {

        val task =  TaskDAO.listData
        task.removeAt(id-1)
    }



    fun getAll(): MutableList<task> {
        return  TaskDAO.listData
    }
}