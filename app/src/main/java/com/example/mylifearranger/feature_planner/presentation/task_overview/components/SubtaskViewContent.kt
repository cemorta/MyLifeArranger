package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.example.mylifearranger.feature_planner.domain.model.Subtask

@Composable
fun SubtaskViewContent(subtasks: List<Subtask>, onSubtaskDelete: (Int) -> Unit, onSubtaskCheckedChange: (Int, Boolean) -> Unit, onSubtaskTitleChange: (Int, String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(subtasks.size) { index ->
                key(subtasks[index].id) {
                    SubtaskItem(subtask = subtasks[index],
                        onDelete = {
                            // delete the subtask
                            subtasks[index].id?.let { subtaskId ->
                                onSubtaskDelete(subtaskId)
                            }
                        },
                        onCheckedChange = {
                            // update the task status
                            subtasks[index].id?.let { subtaskId ->
                                onSubtaskCheckedChange(subtaskId, it)
                            }
                        },
                        onTitleValueChange = {
                            // update the task title
                            subtasks[index].id?.let { subtaskId ->
                                onSubtaskTitleChange(subtaskId, it)
                            }
                        }
                    )
                }
            }
        },
    )
}
