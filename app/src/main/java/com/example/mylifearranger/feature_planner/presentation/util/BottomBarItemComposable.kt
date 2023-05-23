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

@Composable
fun BottomBarItemComposable(
    barItem: BottomTabItem,
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
