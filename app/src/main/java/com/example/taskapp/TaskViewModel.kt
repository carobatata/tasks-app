package com.example.taskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.model.TaskDAO
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDAO) : ViewModel(){

    fun addTask(newTaskName: String){
        val task = Task().apply { taskName = newTaskName}
        viewModelScope.launch {
            dao.insert(task)
        }
    }
}