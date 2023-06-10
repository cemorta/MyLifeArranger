package com.example.mylifearranger.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.navigation.NavController

@Composable
fun BottomBar(
    navController: NavController,
    currentItem: BottomBarItem,
    items: List<BottomBarItem> = BottomBarItem.items
) {
    var width by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size -> width = size.width }
            .background(color = Color.LightGray),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEach { item ->
                BottomBarItemComposable(
                    barItem = item,
                    width = width / items.size,
                    isSelected = item == currentItem,
                    navController
                )
            }
        }
    }
}
