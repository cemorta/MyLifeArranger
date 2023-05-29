package com.example.mylifearranger.feature_planner.presentation.event_details.components

import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.presentation.util.ActionIconButton

fun eventDetailsActionButtons(
    onEdit: () -> Unit,
    onDelete: () -> Unit
): List<ActionIconButton> {
    return listOf(
        ActionIconButton(
            icon = R.drawable.baseline_edit_24,
            contentDescription = "Edit Event",
            onClick = { onEdit() }
        ),
        ActionIconButton(
            icon = R.drawable.baseline_delete_24,
            contentDescription = "Delete Event",
            onClick = { onDelete() }
        )
    )
}