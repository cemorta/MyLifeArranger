package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import toLocalDateTime

@Composable
fun EventRow(event: Event, offset: Float, endOffset: Float, onClick: () -> Unit) {
//    val density = LocalDensity.current
//    val pxOffset = with(density) { offset.dp.toPx() }
    Box(
        modifier = Modifier
            .offset(y = offset.dp)
            .clickable {
                // Call the event details screen
                onClick()
            }
            .fillMaxWidth()
            .background(Color(event.color))
            .height(endOffset.dp - offset.dp)
    ) {
        Text(
            event.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold
        )
    }
}
