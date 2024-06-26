package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import com.example.mylifearranger.feature_planner.presentation.plan_overview.components.PlanOverviewContent
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanOverviewScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: PlanOverviewViewModel = hiltViewModel(),
) {

    val snackbarHostState = remember { SnackbarHostState() }
    // Todo: precautions for null states of sharedViewModel

    LaunchedEffect(Unit) {
        viewModel.setViewModel(sharedViewModel)
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PlanOverviewViewModel.UiAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is PlanOverviewViewModel.UiAction.SavePlan -> {
                    if (viewModel.plan != null) {
                        // pop back to the plan list screen
                        navController.navigate(Screen.PlanViewScreen.route) {
                            popUpTo(Screen.PlanViewScreen.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "Plan Overview",
                isThereBackButton = true,
                navController = navController
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

            PlanOverviewContent(viewModel = viewModel)
        }
    }
}