package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanOverviewScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: PlanOverviewViewModel = hiltViewModel(),
) {
    viewModel.setViewModel(sharedViewModel)
    println("zl" + sharedViewModel.sharedState.value)

    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    viewModel.onEvent(AddEditPlanEvent.SaveEvent)
//                },
//                Modifier.background(
//                    MaterialTheme.colorScheme.background,
//                )
//            ) {
//                // TODO: change icon if it is edit mode
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
//                    contentDescription = "Continue creating plan",
//                    modifier = Modifier.size(36.dp)
//                )
//            }
//        },
        topBar = {
            AppBar(
                title = "Plan Overview",
                isThereBackButton = true,
                navController = navController
            )
        },
//        snackbarHost = {
//            snackbarHostState.currentSnackbarData?.let { snackbarData ->
//                Snackbar(
//                    snackbarData = snackbarData,
//                )
//            }
//        }
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