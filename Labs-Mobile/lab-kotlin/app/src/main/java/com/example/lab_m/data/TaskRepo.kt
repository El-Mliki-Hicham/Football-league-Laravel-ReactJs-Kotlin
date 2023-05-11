package com.example.lab_m.data

import android.provider.SyncStateContract.Helpers.update

class TaskRepo {

    private var taskDAO = TaskDAO()
  fun getAll()=taskDAO.getAll()
    private fun insert(task: task)=taskDAO.insert(task)
    fun delete(id:Int)=taskDAO.delete(id)
    private fun update(task: task)=taskDAO.update(task)
    fun save(task: task){
        if(task.id == 0 ){
            print(task)
            insert(task)
        }else{
            update(task)
        }
    }

}
