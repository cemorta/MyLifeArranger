package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.presentation.day_view.returnClockPosition

@Composable
fun EventRows() {
    Column {
        Spacer(modifier = Modifier.height(returnClockPosition("01:00").dp))
        EventRow("Event 1")
    }
}
