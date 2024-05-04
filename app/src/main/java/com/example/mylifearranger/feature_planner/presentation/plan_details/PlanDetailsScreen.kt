package com.example.mylifearranger.feature_planner.presentation.plan_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.ChangeCompletedAmountDialog
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.PlanTaskBoxes
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.planDetailsActionButtons
import toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanDetailsScreen(
    navController: NavController,
    viewModel: PlanDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    var showCompletedAmountDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBar(
                title = state.planWithTasks?.plan?.title ?: "",
                actionIconButtons = planDetailsActionButtons {
                    // open a dialog to change the completed amount
                    showCompletedAmountDialog = true
                },
                isThereBackButton = true,
                navController = navController
            )
        },
        bottomBar = {
            BottomBar(navController, BottomBarItem.items[1])
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    it.calculateLeftPadding(LayoutDirection.Ltr),
                    it.calculateTopPadding(),
                    it.calculateRightPadding(LayoutDirection.Ltr),
                    it.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {
            if (showCompletedAmountDialog) {
                ChangeCompletedAmountDialog(
                    onConfirm = { newAmount ->
                        // Update logic here
                        viewModel.onAction(PlanDetailsAction.UpdatePlanCompletedAmount(state.planWithTasks?.plan!!, newAmount))
                        showCompletedAmountDialog = false
                    },
                    onDismiss = {
                        showCompletedAmountDialog = false
                    }
                )
            }

            Column {

                Text(text = "Title: ${state.planWithTasks?.plan?.title}")
                Text(text = "Plan Type: ${state.planWithTasks?.plan?.planType}")
                Text(text = "Total Amount: ${state.planWithTasks?.plan?.totalAmount}")
                Text(text = "Completed Amount: ${state.planWithTasks?.plan?.completedAmount}")
                Text(text = "Unit: ${state.planWithTasks?.plan?.unit}")
                Text(text = "Start Range: ${state.planWithTasks?.plan?.startRange}")
                Text(text = "End Range: ${state.planWithTasks?.plan?.endRange}")
                Text(text = "Days: ${state.planWithTasks?.plan?.days}")
                if (state.planWithTasks?.plan?.startDateTimestamp != null) {
                    Text(text = "Start Date: ${state.planWithTasks?.plan?.startDateTimestamp!!.toLocalDateTime()}")
                }
                if (state.planWithTasks?.plan?.endDateTimestamp != null) {
                    Text(text = "End Date: ${state.planWithTasks?.plan?.endDateTimestamp!!.toLocalDateTime()}")
                }
                Text(text = "Is Done: ${state.planWithTasks?.plan?.isDone}")

                // Display the PlanTaskBoxes
                state.planWithTasks?.tasks?.let { planTasks ->
                    PlanTaskBoxes(planTasks)
                }
            }
        }
    }
}