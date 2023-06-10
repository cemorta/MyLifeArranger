package com.example.mylifearranger.feature_planner.presentation.day_view.components

import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.ActionIconButton

fun dayViewActionButtons(
    onTodayClick: () -> Unit,
    onPickDateClick: () -> Unit,
    onViewSettingsClick: () -> Unit,
): List<ActionIconButton> {
    return listOf(
        ActionIconButton(
            icon = R.drawable.baseline_calendar_today_24,
            contentDescription = "Today",
            onClick = { onTodayClick() }),
        ActionIconButton(
            icon = R.drawable.baseline_calendar_month_24,
            contentDescription = "Pick Date",
            onClick = { onPickDateClick() }),
        ActionIconButton(
            icon = R.drawable.baseline_settings_24,
            contentDescription = "View Settings",
            onClick = { onViewSettingsClick() })
    )
}