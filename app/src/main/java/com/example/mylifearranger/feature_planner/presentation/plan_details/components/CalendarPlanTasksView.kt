package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import toLocalDateTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CalendarPlanTasksView(
    startDate: LocalDateTime,
    endDate: LocalDateTime,
    workingDays: Array<Boolean>
) {
    // display 7 squares in a row
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .border(3.dp, Color.Gray, RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
//                .padding(8.dp)
                .verticalScroll(
                    scrollState
                )
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
        ) {
            Row {
                repeat(7) {
                    Box(
                        modifier =
                        Modifier.weight(1f)
                    )
                    {
                        Text(
                            text = LocalContext.current.resources.getStringArray(R.array.days_of_week)[it],
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            // Get the monday of the week of the start date
            val mondayOfStartDate = startDate.toLocalDate()
                .minusDays(startDate.toLocalDate().dayOfWeek.value.toLong() - 1).atStartOfDay()
            // Get the sunday of the week of the end date
            val sundayOfEndDate =
                endDate.toLocalDate().plusDays(7 - endDate.toLocalDate().dayOfWeek.value.toLong())
                    .atStartOfDay()

            // Get the number of days between the start and end date
            val daysBetween =
                sundayOfEndDate.toLocalDate().toEpochDay() - mondayOfStartDate.toLocalDate()
                    .toEpochDay()
            repeat((daysBetween / 7 + 1).toInt()) { week ->
                Row {
                    repeat(7) {
                        val date = mondayOfStartDate.plusDays((week * 7 + it).toLong())
                        PlanTaskSquare(
                            date,
                            date.toLocalDate() in startDate.toLocalDate()..endDate.toLocalDate() &&
                                    workingDays[it]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlanTaskSquare(date: LocalDateTime, isWorkingDay: Boolean) {
    val widthSizePx =
        LocalContext.current.resources.displayMetrics.widthPixels // Screen width in pixels
    val density = LocalDensity.current.density // Current screen density

    // Converting pixels to dp
    val widthSizeDp = widthSizePx / density

    // Calculate the size for the square Card
    val squareSize = (widthSizeDp / 7).dp

    Card(
        modifier = Modifier
            .size(squareSize, squareSize)
            .clickable {
                // Handle click event here
            },
        shape = RoundedCornerShape(8.dp)  // Rounded corners for the card
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color =
                    if (isWorkingDay) {
                        Color.Yellow
                    } else {
                        Color.LightGray
                    },
                )
//
//                    } else if (planTask.performedDateTimestamp
//                            .toLocalDateTime()
//                            .isBefore(
//                                LocalDate
//                                    .now()
//                                    .atStartOfDay()
//                            )
//                    ) {
//                        Color.Red
//                    } else {
//                        Color.LightGray
//                    },
//                )  // Background color of the card content
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))  // Border for the card
                .padding(8.dp)  // Padding inside the card
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Text(
                text = date.dayOfMonth.toString(),
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPlanTasksViewPreview() {
    MaterialTheme {
        CalendarPlanTasksView(
            startDate = LocalDate.now().atStartOfDay(),
            endDate = LocalDate.now().plusDays(20).atStartOfDay(),
            workingDays = arrayOf(true, true, true, true, true, true, true)
        )
    }
}