package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.util.Locale


@Composable
fun PlanInfoRow(startDate: LocalDateTime, endDate: LocalDateTime, workingDays: String, totalAmount: Int, completedAmount: Int, unit: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Display the plan information here
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First rectangle (larger)
            Box(
                modifier = Modifier
                    .weight(2f) // Takes 2 parts of the available space
                    .background(Color.Gray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${startDate.dayOfMonth} ${startDate.month.getDisplayName(
                        java.time.format.TextStyle.SHORT,
                        Locale.getDefault()
                    )} - ${endDate.dayOfMonth} ${endDate.month.getDisplayName(
                        java.time.format.TextStyle.SHORT,
                        Locale.getDefault()
                    )} \n${workingDays}",
                    color = Color.White
                )
            }

            // Second rectangle (smaller)
            Box(
                modifier = Modifier
                    .weight(1f) // Takes 1 part of the available space
                    .background(Color.DarkGray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${completedAmount}/${totalAmount} ${unit}\nCompleted",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanInfoRowPreview() {
    MaterialTheme {
        PlanInfoRow(
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now().plusDays(2),
            workingDays = "Mon, Tue, Wed",
            totalAmount = 10,
            completedAmount = 5,
            unit = "kg"
        )
    }
}