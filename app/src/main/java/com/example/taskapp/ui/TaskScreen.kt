package com.example.taskapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.model.Task

@Composable

fun TaskScreen(
    createTask: (String) -> Unit,
    taskListUiState: List<Task>,
    getAllTasks: () -> Unit
) {
    var taskInput by remember { mutableStateOf("") }
    var renderListAgain by remember { mutableStateOf(false) }

    LaunchedEffect(renderListAgain) {
        getAllTasks()
        renderListAgain = false
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = taskInput,
            onValueChange = { taskInput = it },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { createTask(taskInput); taskInput = "" ; renderListAgain = true}, modifier = Modifier
                .align(androidx.compose.ui.Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Save Task")
        }

        LazyColumn {
            items(taskListUiState.size) {
                Column(Modifier.padding(5.dp)) {
                    Text(text = "ID: ${taskListUiState[it].taskId}")
                    Text(text = "Name: ${taskListUiState[it].taskName}")
                    Text(text = "Complete: ${taskListUiState[it].isDone}")
                }

            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen({}, listOf(Task(1, "Task 1", false), Task(2, "Task 2", true)), {})
}