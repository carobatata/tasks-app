package com.example.taskapp.model

import javax.inject.Inject

class TaskRepository @Inject constructor(private val dao: TaskDAO) {

    suspend fun add(newTask: Task) : Result<Boolean> {
        return try {
            dao.insert(newTask)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAll() : Result<List<Task>> {
        return try {
            val response = dao.getAll()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun delete(task: Task) : Result<Boolean> {
        return try {
            dao.delete(task)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}