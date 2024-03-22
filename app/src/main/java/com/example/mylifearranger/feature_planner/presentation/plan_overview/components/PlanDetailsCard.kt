package com.example.mylifearranger.feature_planner.presentation.plan_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlanDetailsCard(
    planName: String,
    planDays: String,
    totalAmount: Int,
    unit: String,
    startRange: Int? = null,
    endRange: Int? = null
) {

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
    {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Title: $planName")
            Text(text = "Days: $planDays")
            // If startRange and endRange are not null, show the range
            if (startRange != null && endRange != null) {
                Text(text = "Range: $startRange - $endRange")
            }
            Text(text = "Total: $totalAmount $unit")
        }
    }
}