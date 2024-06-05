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
import com.example.mylifearranger.core.presentation.util.returnDayArrayByBitMasking
import com.example.mylifearranger.core.presentation.util.returnDayStringByBitMasking
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.CalendarPlanTasksView
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.ChangeCompletedAmountDialog
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.PlanInfoRow
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.PlanTaskBoxes
import com.example.mylifearranger.feature_planner.presentation.plan_details.components.PlanTaskList
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
                        viewModel.onAction(
                            PlanDetailsAction.UpdatePlanCompletedAmount(
                                state.planWithTasks?.plan!!,
                                newAmount
                            )
                        )
                        showCompletedAmountDialog = false
                    },
                    onDismiss = {
                        showCompletedAmountDialog = false
                    }
                )
            }

            Column {

                // Display the plan information
                state.planWithTasks?.plan?.let { plan ->
                    PlanInfoRow(
                        startDate = state.planWithTasks!!.plan.startDateTimestamp.toLocalDateTime(),
                        endDate = state.planWithTasks!!.plan.endDateTimestamp.toLocalDateTime(),
                        workingDays = returnDayStringByBitMasking(
                            state.planWithTasks!!.plan.days,
                            androidx.compose.ui.platform.LocalContext.current.resources.getStringArray(
                                com.example.mylifearranger.R.array.days_of_week
                            ),
                        ),
                        totalAmount = state.planWithTasks?.plan?.totalAmount!!,
                        completedAmount = plan.completedAmount,
                        unit = plan.unit
                    )

                    // Display calendar with plan tasks
                    CalendarPlanTasksView(
                        startDate =
                        state.planWithTasks!!.plan.startDateTimestamp.toLocalDateTime(),
                        endDate =
                        state.planWithTasks!!.plan.endDateTimestamp.toLocalDateTime(),
                        planTasks = state.planWithTasks!!.tasks,
                    )

                    // Display the plan tasks
                    PlanTaskList(planTasks = state.planWithTasks?.tasks ?: emptyList())
                }
            }
        }
    }
}