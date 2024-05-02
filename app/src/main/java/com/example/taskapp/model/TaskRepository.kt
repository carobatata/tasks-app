package com.example.taskapp.model

import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDAO) {

    suspend fun add(newTask: Task) {
        return dao.insert(newTask)
    }

    suspend fun getAll() : List<Task> {
        return dao.getAll()
    }
}