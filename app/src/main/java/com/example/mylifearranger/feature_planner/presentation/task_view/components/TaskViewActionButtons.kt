package com.example.mylifearranger.feature_planner.presentation.task_view.components

import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import com.example.mylifearranger.feature_planner.presentation.util.ActionIconButton

fun taskViewActionButtons(
    taskType: TaskType,
    onYearlyTasks: () -> Unit,
    onMonthlyTasks: () -> Unit,
    onDailyTasks: () -> Unit
): List<ActionIconButton> {

    when (taskType) {
        TaskType.YEARLY -> {
            return listOf(
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_month_24,
                    contentDescription = "Monthly Tasks",
                    onClick = { onMonthlyTasks() }
                ),
                ActionIconButton(
                    icon = R.drawable.baseline_view_agenda_24,
                    contentDescription = "Daily Tasks",
                    onClick = { onDailyTasks() }
                ),
            )
        }
        TaskType.MONTHLY -> {
            return listOf(
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_view_month_24,
                    contentDescription = "Yearly Tasks",
                    onClick = { onYearlyTasks() }
                ),
                ActionIconButton(
                    icon = R.drawable.baseline_view_agenda_24,
                    contentDescription = "Daily Tasks",
                    onClick = { onDailyTasks() }
                ),
            )
        }
        TaskType.DAILY -> {
            return listOf(
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_view_month_24,
                    contentDescription = "Yearly Tasks",
                    onClick = { onYearlyTasks() }
                ),
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_month_24,
                    contentDescription = "Monthly Tasks",
                    onClick = { onMonthlyTasks() }
                ),
            )
        }
        else -> {

            return listOf(
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_view_month_24,
                    contentDescription = "Yearly Tasks",
                    onClick = { onYearlyTasks() }
                ),
                ActionIconButton(
                    icon = R.drawable.baseline_calendar_month_24,
                    contentDescription = "Monthly Tasks",
                    onClick = { onMonthlyTasks() }
                ),
                ActionIconButton(
                    icon = R.drawable.baseline_view_agenda_24,
                    contentDescription = "Daily Tasks",
                    onClick = { onDailyTasks() }
                ),
            )
        }
    }
}