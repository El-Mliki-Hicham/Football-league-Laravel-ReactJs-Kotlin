package com.example.lab_m

import android.media.CamcorderProfile.getAll
import com.example.lab_m.data.TaskDAO
import com.example.lab_m.data.TaskRepo
import com.example.lab_m.data.task

fun main(){
  var repo = TaskRepo()
    //save
   var task =  task(id = 5, Task = "nada", Priority = 100,System.currentTimeMillis())
    repo.save(task)

    //delete
    repo.delete(2)
    //getAll
    var tasks = repo.getAll()
for (data in tasks) {
    println(data.Task)
}
}