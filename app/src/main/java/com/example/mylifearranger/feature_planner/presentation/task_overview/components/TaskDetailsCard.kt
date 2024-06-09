package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import toLocalDateTime
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TaskDetailsCard(
    taskDurationHour: String? = null,
    taskDurationMinute: String? = null,
    dueDateTime: LocalDateTime? = null,
    plannedDateTime: LocalDateTime? = null
) {
    if ((taskDurationHour != null && taskDurationMinute != null) || dueDateTime != null || plannedDateTime != null) {
        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Task Details", modifier = Modifier.padding(start = 10.dp, top = 5.dp))
            Column(modifier = Modifier.padding(20.dp, 10.dp, 10.dp, 10.dp)) {
                taskDurationHour?.let {
                    Text(text = "Estimated Duration: ${taskDurationHour}h ${taskDurationMinute}m")
                }
                dueDateTime?.let {
                    Text(
                        text = "Due Date: " +
                                "${dueDateTime.dayOfMonth} " +
                                "${
                                    dueDateTime.month.getDisplayName(
                                        TextStyle.FULL,
                                        Locale.getDefault()
                                    )
                                } " +
                                "${dueDateTime.year}"
                    )
                }
                plannedDateTime?.let {
                    Text(
                        text = "Planned Date: " +
                                "${plannedDateTime.dayOfMonth} " +
                                "${
                                    plannedDateTime.month.getDisplayName(
                                        TextStyle.FULL,
                                        Locale.getDefault()
                                    )
                                } " +
                                "${plannedDateTime.year}"
                    )
                }
            }
        }
    }
}