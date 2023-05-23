package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EventRow(eventName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(alpha = 0.5f))
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(eventName, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
    }
}
