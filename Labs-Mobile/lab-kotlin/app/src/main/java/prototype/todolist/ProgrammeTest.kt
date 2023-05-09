package prototype.todolist

import prototype.todolist.data.TaskDao
import prototype.todolist.data.TaskEntry
import prototype.todolist.data.TaskRepository

fun main (){

    val taskRepository = TaskRepository()
    // Ajouter une tâche
    val task = TaskEntry(1,"Task 1",1,System.currentTimeMillis())
    taskRepository.save(task);

    // Modifier une tâche
    val updateTask = TaskEntry(1 , "task nada",1,System.currentTimeMillis())
     taskRepository.save(updateTask)

    // Suprimer une tâche
    taskRepository.delete(1)

    // Afficher les tâches
     var data = taskRepository.getAllTasks()
     for(task in data){
        println(task.title)
     }




}