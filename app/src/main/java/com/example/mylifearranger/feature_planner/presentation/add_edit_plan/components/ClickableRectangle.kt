package com.example.mylifearranger.feature_planner.presentation.add_edit_plan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ClickableRectangle(text: String, color: Color = Color.White, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onClick() }
            .border(1.dp, Color.Black)
            .background(color = color)
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}