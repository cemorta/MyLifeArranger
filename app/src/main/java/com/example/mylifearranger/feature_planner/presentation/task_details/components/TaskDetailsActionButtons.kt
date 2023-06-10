package com.example.mylifearranger.feature_planner.presentation.task_details.components

import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.ActionIconButton

fun taskDetailsActionButtons(
    onEdit: () -> Unit,
    onDelete: () -> Unit
): List<ActionIconButton> {
    return listOf(
        ActionIconButton(
            icon = R.drawable.baseline_edit_24,
            contentDescription = "Edit Task",
            onClick = { onEdit() }
        ),
        ActionIconButton(
            icon = R.drawable.baseline_delete_24,
            contentDescription = "Delete Task",
            onClick = { onDelete() }
        )
    )
}