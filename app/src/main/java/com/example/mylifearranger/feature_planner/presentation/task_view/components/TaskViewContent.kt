package com.example.mylifearranger.feature_planner.presentation.task_view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.presentation.task_view.TaskViewState

@Composable
fun TaskViewContent(state: TaskViewState, onTaskClick: (Int) -> Unit) {

    LazyColumn(content = {
        items(state.tasks.size) { index ->
            TaskRow(task = state.tasks[index]) { taskId ->
                onTaskClick(taskId)
            }
        }
    })

}