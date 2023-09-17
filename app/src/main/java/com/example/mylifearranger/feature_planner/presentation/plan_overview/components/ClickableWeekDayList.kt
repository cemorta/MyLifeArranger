package com.example.mylifearranger.feature_planner.presentation.plan_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.R

@Composable
fun ClickableWeekDayList(dayAmount: Array<Int>) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val daysOfWeek =
            androidx.compose.ui.platform.LocalContext.current.resources.getStringArray(R.array.days_of_week)
        for (i in 0..6) {

            ClickableOneWeekPlanOverviewRectangle(
                dayInfo = daysOfWeek[i],
                modifier = Modifier.weight(1f),
                dayAmount = if (dayAmount[i] == -1) null else dayAmount[i],
                color = if (dayAmount[i] == -1) Color.White else Color.LightGray
            ) {

            }
        }
    }
}