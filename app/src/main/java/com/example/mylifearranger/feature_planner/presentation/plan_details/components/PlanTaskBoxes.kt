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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlanTaskBoxes(planTasks: List<PlanTask>) {
    // FlowRow will automatically wrap the PlanTaskBox composables to the next line
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,  // Evenly space the items in a row
    ) {
        planTasks.forEach { planTask ->
            PlanTaskBox(planTask = planTask)
        }
    }
}

@Composable
fun PlanTaskBox(planTask: PlanTask) {
    // Using Card for a better visual appearance
    Card(
        modifier = Modifier
            .padding(4.dp)  // Padding around the card
            .size(160.dp, 80.dp)  // Updated size for better readability
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
                    if (planTask.isDone) {
                        Color.Green

                    } else if (planTask.performedDateTimestamp
                            .toLocalDateTime()
                            .isBefore(
                                LocalDate
                                    .now()
                                    .atStartOfDay()
                            )
                    ) {
                        Color.Red
                    } else {
                        Color.LightGray
                    },
                )  // Background color of the card content
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))  // Border for the card
                .padding(8.dp)  // Padding inside the card
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Column {
                // Displaying the date, consider formatting this date as needed
                Text(
                    text = planTask.performedDateTimestamp.toLocalDateTime()
                        .format(DateTimeFormatter.ISO_DATE).toString(),
                    color = Color.Black  // Text color
                )
                Text(
                    text = planTask.amountToComplete.toString(),
                    color = Color.Black  // Text color
                )
            }
        }
    }
}
