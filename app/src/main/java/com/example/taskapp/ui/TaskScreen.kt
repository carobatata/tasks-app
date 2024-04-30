package com.example.taskapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp

@Composable
fun TaskScreen() {
    var taskInput by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {

        TextField(value = taskInput, onValueChange = { taskInput = it }, modifier = Modifier.fillMaxWidth())

        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .align(androidx.compose.ui.Alignment.CenterHorizontally)
            .padding(vertical = 16.dp)) {
            Text(text = "Save Task")
        }

        LazyColumn {
            items(3) {
                Column(Modifier.padding(5.dp)) {
                    Text(text = "ID: $it")
                    Text(text = "Name: Task name")
                    Text(text = "Complete: false")
                }

            }

        }
    }



}

@Preview(showSystemUi = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}