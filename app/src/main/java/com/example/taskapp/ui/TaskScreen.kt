package com.example.taskapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.model.Task

@Composable

fun TaskScreen(
    createTask: (String) -> Unit,
    taskListUiState: List<Task>,
    getAllTasks: () -> Unit,
    deleteTask: (Task) -> Unit
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
            onClick = { createTask(taskInput); taskInput = ""; renderListAgain = true },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Save Task")
        }

        LazyColumn {
            items(taskListUiState.size) {
                Column(Modifier.padding(5.dp)) {
                    OutlinedCard(
                        Modifier.padding(5.dp),
                        border = BorderStroke(1.5.dp, Color.DarkGray)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Row {
                                Text(text = "ID: ${taskListUiState[it].taskId}", fontSize = 18.sp)
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "delete task icon",
                                    modifier = Modifier.clickable {
                                        deleteTask(taskListUiState[it])
                                        renderListAgain = true
                                    }
                                )
                            }
                            Text(text = "Name: ${taskListUiState[it].taskName}", fontSize = 18.sp)
                            Text(text = "Complete: ${taskListUiState[it].isDone}", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen(
        {},
        listOf(Task(1, "Task 1", false), Task(2, "Task 2", true)),
        {},
        {}
    )
}