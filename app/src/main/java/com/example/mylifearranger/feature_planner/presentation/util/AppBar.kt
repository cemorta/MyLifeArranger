package com.example.mylifearranger.feature_planner.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mylifearranger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    actionIconButtons: List<ActionIconButton> = emptyList(),
    isThereBackButton: Boolean = false,
    navController: NavController? = null
) {
    TopAppBar(title = { Text(text = title) }, actions = {
        actionIconButtons.forEach { actionIconButton ->
            ActionIconButtonComposable(
                icon = actionIconButton.icon,
                contentDescription = actionIconButton.contentDescription,
                onClick = actionIconButton.onClick
            )
        }
    },
        navigationIcon = {
            if (isThereBackButton) {
                run {
                    ActionIconButtonComposable(
                        icon = R.drawable.baseline_arrow_back_24,
                        contentDescription = "Back"
                    ) { navController?.navigateUp() }
                }
            }
        }
    )
}
