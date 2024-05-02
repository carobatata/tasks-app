package com.example.taskapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.model.TaskRepository
import com.example.taskapp.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel(){

    private val _tasksList = MutableStateFlow<List<Task>>(emptyList())
    val tasksList: StateFlow<List<Task>> = _tasksList.asStateFlow()

    fun addTask(newTaskName: String){
        val task = Task().apply { taskName = newTaskName}
        viewModelScope.launch {
            repository.add(task)
        }
    }

    fun getAllTasks() {
        viewModelScope.launch {
            val result = repository.getAll()
            _tasksList.value = result
        }
    }
}