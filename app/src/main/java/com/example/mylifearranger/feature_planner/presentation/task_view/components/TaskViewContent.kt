package com.example.mylifearranger.feature_planner.presentation.task_view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mylifearranger.feature_planner.presentation.task_view.TaskViewState

@Composable
fun TaskViewContent(state: TaskViewState, onTaskClick: (Int) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        content = {
        items(state.tasks.size) { index ->
            TaskRow(task = state.tasks[index]) { taskId ->
                onTaskClick(taskId)
            }
        }
    })

}