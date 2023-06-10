package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddEditPlanScreenPreview() {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    viewModel.onEvent(AddEditEventEvent.SaveEvent)
                },
                Modifier.background(
                    MaterialTheme.colorScheme.background,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save plan"
                )
            }
        },
        topBar = {
            AppBar(
                title = "Plan 1",
                isThereBackButton = true,
//                navController = navController
            )
        },
        snackbarHost = {
            snackbarHostState.currentSnackbarData?.let { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                )
            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateTopPadding(),
                    paddingValues.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}