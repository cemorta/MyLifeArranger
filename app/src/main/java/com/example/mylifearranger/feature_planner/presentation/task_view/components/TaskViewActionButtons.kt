package com.example.mylifearranger.feature_planner.presentation.task_view.components

import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.presentation.util.ActionIconButton

fun taskViewActionButtons(
): List<ActionIconButton> {
    return listOf(
        ActionIconButton(
            icon = R.drawable.baseline_edit_24,
            contentDescription = "Edit Event",
            onClick = {  }
        ),
        ActionIconButton(
            icon = R.drawable.baseline_delete_24,
            contentDescription = "Delete Event",
            onClick = {  }
        )
    )
}