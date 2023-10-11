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
fun StartEndDatesOverviewCard(
    startDate: String,
    endDate: String,
    dayCount: Int
) {

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
    {
        Text(text = "Start & End Dates", modifier = Modifier.padding(start = 10.dp, top = 5.dp))
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "$startDate - $endDate")
            Text(text = "$dayCount plan day(s) between")
        }
    }
}