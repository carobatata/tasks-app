package com.example.taskapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(

    @PrimaryKey(autoGenerate = true) var taskId: Long = 0L,

    @ColumnInfo(name = "task_name") var taskName: String = "",

    @ColumnInfo(name = "task_done") var isDone: Boolean = false
)

//PrimaryKey is to identify single record, cannot contain duplicate values.
// Task is the data class which defines a table
// task DAO is the interface which specifies the data access method.