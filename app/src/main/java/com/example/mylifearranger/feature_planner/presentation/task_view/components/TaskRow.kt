package com.example.mylifearranger.feature_planner.presentation.task_view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.Task

@Composable
fun TaskRow(task: Task, onTaskClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // navigate to the task overview screen
                task.id?.let { onTaskClick(it) }
            }
            .background(color = Color.LightGray)
            .height(50.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column(Modifier.padding(start = 5.dp)) {
            Text(text = task.title)
        }
    }
}