package com.example.mylifearranger.feature_planner.presentation.plan_overview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClickableOneWeekPlanOverviewRectangle(
    dayInfo: String,
    dayAmount: Int? = null,
    color: Color = Color.White,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onClick() }
            .border(1.dp, Color.Black)
            .background(color = color)
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(dayInfo)
            dayAmount?.let {
                Text(it.toString())
            }
        }
    }
}