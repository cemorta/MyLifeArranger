package com.example.mylifearranger.feature_planner.presentation.util

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

data class ActionIconButton(val icon: Int, val contentDescription: String, val onClick: () -> Unit)

@Composable
fun ActionIconButtonComposable(icon: Int, contentDescription: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(id = icon), contentDescription = contentDescription)
    }
}
