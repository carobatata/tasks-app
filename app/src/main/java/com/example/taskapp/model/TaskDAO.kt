package com.example.taskapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.StateFlow

//Data access object includes all methods the app needs to insert, read, update, and delete the data.
//How the app will access the tables data.

@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(task: Task)

    @Insert
    suspend fun insertAll(tasks: List<Task>)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    suspend fun getAll(): List<Task>


    // with live data the app can use them to be notified when the data changes
    //no need to put susepnd because with live data room already uses background thread.
//    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
//    fun get(taskId: Long) : StateFlow<Task>
//
//    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
//    fun getAll() : StateFlow<List<Task>>

}