package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.ActionIconButton

fun planDetailsActionButtons(
    onClick: () -> Unit,
): List<ActionIconButton> {
    return listOf(
        ActionIconButton(
            icon = R.drawable.baseline_wb_sunny_24,
            contentDescription = "Change Completed Amount",
            onClick = { onClick() }
        ),
    )
}