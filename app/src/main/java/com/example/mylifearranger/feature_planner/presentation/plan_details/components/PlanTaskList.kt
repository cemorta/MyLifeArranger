package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import toLocalDateTime
import toTimestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlanTaskList(planTasks: List<PlanTask>) {
    LazyColumn {
        items(planTasks.size) { index ->
            PlanTaskCard(planTasks[index])
        }
    }
}

@Composable
fun PlanTaskCard(planTask: PlanTask) {
    ListItem(headlineContent = {
        Text(
            text = "${planTask.performedDateTimestamp.toLocalDateTime().dayOfMonth} ${
                planTask.performedDateTimestamp.toLocalDateTime().month.getDisplayName(
                    java.time.format.TextStyle.SHORT,
                    java.util.Locale.getDefault()
                )
            }"
        )
    }, trailingContent = {
        Text(
            text = "Amount: ${planTask.amountToComplete}"
        )
    }, modifier = Modifier
        .fillMaxWidth()
        .clickable {
            // Handle click event here
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PlanTaskListPreview() {
    MaterialTheme {
        PlanTaskList(
            planTasks = listOf(
                PlanTask(
                    title = "Task 1",
                    description = "Description 1",
                    performedDateTimestamp = LocalDateTime.now().toTimestamp(),
                    amountToComplete = 10,
                    assignedPlanId = 1,
                    isDone = false,
                    setPlannedTime = false,
                    taskDuration = null,
                    id = null,
                    assignedEventId = null,
                ),
                PlanTask(
                    title = "Task 2",
                    description = "Description 2",
                    performedDateTimestamp = LocalDateTime.now().toTimestamp(),
                    amountToComplete = 10,
                    assignedPlanId = 1,
                    isDone = false,
                    setPlannedTime = false,
                    taskDuration = null,
                    id = null,
                    assignedEventId = null,
                )
            )
        )
    }
}