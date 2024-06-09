package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.presentation.task_overview.TaskOverviewAction
import com.example.mylifearranger.feature_planner.presentation.task_overview.TaskOverviewViewModel

@Composable
fun TaskSubtasksCard(
    subtasks: List<Subtask>,
    viewModel: TaskOverviewViewModel
) {
    var newAddedSubtaskSize by remember { mutableIntStateOf(0) }
    if (subtasks.isNotEmpty()) {
        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Subtasks",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )
            Column {
                SubtaskViewContent(subtasks, onSubtaskDelete = {
                    // delete the subtask
                   viewModel.onAction(TaskOverviewAction.RemoveSubtask(it))

                }, onSubtaskCheckedChange = { id, isDone ->
                    viewModel.onAction(TaskOverviewAction.UpdateSubtaskStatus(id, isDone))
                },
                    onSubtaskTitleChange = { id, title ->
                        viewModel.onAction(TaskOverviewAction.UpdateSubtaskTitle(id, title))
                    }
                )
            }
        }
    }
    // Add Subtask Button
    Button(
        onClick = {
            newAddedSubtaskSize--
            viewModel.onAction(TaskOverviewAction.AddNewSubtask("", newAddedSubtaskSize))
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(1f)
    ) {
        Text(text = "Add Subtask")
    }

//    Column {
//        for (subtask in subtasks) {
//            SubtaskItem(subtask, onDelete = { subtasks.remove(subtask) })
//        }
//    }
}