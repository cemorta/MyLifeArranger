package com.example.mylifearranger.feature_planner.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import com.example.mylifearranger.R

data class BottomBarItem(val itemText: String, val icon: Int) {
    companion object {
        val items = listOf(
            BottomBarItem("Day", R.drawable.baseline_view_day_24),
            BottomBarItem("Plans", R.drawable.baseline_menu_book_24),
            BottomBarItem("Goals", R.drawable.baseline_task_alt_24),
            BottomBarItem("Calendar", R.drawable.baseline_calendar_view_month_24),
            BottomBarItem("Settings", R.drawable.baseline_settings_24)
        )
    }
}

@Composable
fun BottomBarItemComposable(
    barItem: BottomBarItem,
    width: Int,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onItemClick() }
            .width(with(LocalDensity.current) { width.toDp() })
            .background(
                color = if (isSelected) Color.Blue else Color.LightGray,
            )
    ) {
        Icon(painter = painterResource(id = barItem.icon), contentDescription = null)
        Text(barItem.itemText)
    }
}
