package com.example.mylifearranger.feature_planner.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.mylifearranger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(title = { Text(text = title) }, actions = {
        ActionIconButton(
            icon = R.drawable.baseline_calendar_today_24,
            contentDescription = "Today",
            onClick = { println("hello") })
        ActionIconButton(
            icon = R.drawable.baseline_calendar_month_24,
            contentDescription = "Pick Date",
            onClick = { println("hello") })
        ActionIconButton(
            icon = R.drawable.baseline_settings_24,
            contentDescription = "View Settings",
            onClick = { println("hello") })
    }
    )
}
