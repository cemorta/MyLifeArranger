package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mylifearranger.feature_planner.domain.model.Event

@Composable
fun TimelineView(events: List<Event>, onEventClick: (Event) -> Unit) {
    val scrollState = rememberScrollState()

    // Create a list of hours to display in the timeline
    val hours = List(24) { String.format("%02d:00", it) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(),
//            verticalArrangement = Arrangement.spacedBy(60.dp) // Add space between items
        ) {
            for (hour in hours) {
                Text(hour, modifier = Modifier.height(80.dp), fontSize = 12.sp)
            }
        }
        EventRows(events, onEventClick)
    }
}
