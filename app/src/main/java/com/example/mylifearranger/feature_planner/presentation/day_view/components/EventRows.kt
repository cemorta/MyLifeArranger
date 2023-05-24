package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mylifearranger.feature_planner.domain.model.Event
import toLocalDateTime

@Composable
fun EventRows(events: List<Event>, modifier: Modifier) {
    Box(modifier = modifier) {
//        for (i in events.indices) {
//            EventRow(events[i], returnClockPosition(String.format("%02d:%02d", events[i].start.hour, events[i].start.minute)))
//        }
        for (event in events) {
            EventRow(event, returnClockPosition(String.format("%02d:%02d", event.startTimestamp.toLocalDateTime().hour, event.startTimestamp.toLocalDateTime().minute)), returnClockPosition(String.format("%02d:%02d", event.endTimestamp.toLocalDateTime().hour, event.endTimestamp.toLocalDateTime().minute)))
        }
    }
//        for (event in events) {
//            println(event.start.hour.toString() + ":" + String.format("%02d", event.start.minute))
//            Spacer(modifier = Modifier.height(returnClockPosition(event.start.hour.toString() + ":" + String.format("%02d", event.start.minute)).dp))
//            EventRow(event.title)
//        }
}

fun returnClockPosition(time: String): Float {
    val initialPadding = 11
    val paddingBetweenHours: Float = 81.5F
    val paddingBetweenMinutes: Float = 1.35F

    val hour = time.substring(0, 2).toInt()
    val minutes = time.substring(3, 5).toInt()
    val result: Float =
        hour * paddingBetweenHours + initialPadding + minutes * paddingBetweenMinutes
    return result
}

fun returnRangeOfTwoTimes(time1: String, time2: String): Float {
    val initialPadding = 11
    val paddingBetweenHours: Float = 81.5F
    val paddingBetweenMinutes: Float = 1.35F

    val hour1 = time1.substring(0, 2).toInt()
    val minutes1 = time1.substring(3, 5).toInt()
    val result1: Float =
        hour1 * paddingBetweenHours + initialPadding + minutes1 * paddingBetweenMinutes

    val hour2 = time2.substring(0, 2).toInt()
    val minutes2 = time2.substring(3, 5).toInt()
    val result2: Float =
        hour2 * paddingBetweenHours + initialPadding + minutes2 * paddingBetweenMinutes

    return result2 - result1
}