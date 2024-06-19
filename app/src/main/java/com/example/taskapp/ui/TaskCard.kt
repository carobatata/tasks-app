package com.example.taskapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.model.Task

@Composable
fun TaskCard(
    taskListUiState: List<Task>,
    it: Int,
    deleteTask: (Task) -> Unit,
    renderListAgain: Boolean
) {
    var renderListAgain1 = renderListAgain
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
                        renderListAgain1 = true
                    }
                )
            }
            Text(text = "Name: ${taskListUiState[it].taskName}", fontSize = 18.sp)
            Text(text = "Complete: ${taskListUiState[it].isDone}", fontSize = 18.sp)
        }
    }
}