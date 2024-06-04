package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import java.time.format.DateTimeFormatter

@Composable
fun CalendarPlanTasksView() {
    // display 7 squares in a row
    Box(
        modifier = Modifier
            .defaultMinSize(
                minWidth = Dp.Unspecified,
                minHeight = 200.dp
            )
            .fillMaxWidth()
            .fillMaxHeight(
                fraction = 0.5f
            )
            .border(3.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
//                .padding(top = 4.dp, bottom = 4.dp),
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
            Row {
                // display 7 squares in a row
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
            }
            Row {
                // display 7 squares in a row
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
                PlanTaskSquare()
            }

        }
    }

}

@Composable
fun PlanTaskSquare() {
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
//                .background(
//                    color =
//                    if (planTask.isDone) {
//                        Color.Green
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
                text = "Task",
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun CalendarPlanTasksViewPreview() {
    MaterialTheme {
        CalendarPlanTasksView()
    }
}