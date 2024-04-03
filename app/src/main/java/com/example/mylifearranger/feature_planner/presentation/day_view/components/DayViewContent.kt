package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import java.time.LocalDate

@Composable
fun DayViewContent(
    selectedDate: LocalDate,
    events: List<Event>,
    planTasks: List<PlanTask>,
    weekDaysRowState: LazyListState,
    onDaySelected: (LocalDate) -> Unit,
    onEventClick: (Event) -> Unit
) {
    Column {
        WeekDaysRow(selectedDate, weekDaysRowState, planTasks) { date ->
            onDaySelected(date)
        }
        HorizontalDivider()
        TimelineView(events, onEventClick)
    }
}

//@Composable
//@Preview(showBackground = true)
//fun PreviewDayViewContent() {
//    // Dummy Events
//    val dummyEvents = listOf(
//        Event(
//            title = "Meeting",
//            description = "Team meeting at 10 AM",
//            startTimestamp = LocalDateTime.now().withHour(10).toInstant(ZoneOffset.UTC)
//                .toEpochMilli(),
//            endTimestamp = LocalDateTime.now().withHour(11).toInstant(ZoneOffset.UTC)
//                .toEpochMilli(),
//            color = Color.Red.toArgb(),
//            isAllDay = false,
//            iconResName = null,
//            id = 1
//        ),
//        Event(
//            title = "Lunch",
//            description = "Lunch break",
//            startTimestamp = LocalDateTime.now().withHour(12).toInstant(ZoneOffset.UTC)
//                .toEpochMilli(),
//            endTimestamp = LocalDateTime.now().withHour(13).toInstant(ZoneOffset.UTC)
//                .toEpochMilli(),
//            color = Color.Green.toArgb(),
//            isAllDay = false,
//            iconResName = null,
//            id = 2
//        )
//    )
//
//    DayViewContent(
//        selectedDate = LocalDate.parse("2023-08-10"),
//        events = dummyEvents,
//        onDaySelected = { },
//        onEventClick = { }
//    )
//}