package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.util.returnDayStringByBitMasking
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import toLocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanOverviewScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: PlanOverviewViewModel = hiltViewModel(),
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.setViewModel(sharedViewModel)
        println("zl" + sharedViewModel.sharedState.value)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(PlanOverviewAction.SavePlan)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
//                // Title of the Plan
//                Text(
//                    text = sharedViewModel.getSharedState()?.title ?: "No title",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                // Plan type
//                Text(
//                    text = sharedViewModel.getSharedState()?.planType.toString(),
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                // Total amount and unit
//                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
//                    Text(
//                        text = "${sharedViewModel.getSharedState()!!.completedAmount} / ${sharedViewModel.getSharedState()!!.totalAmount}",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface,
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        text = sharedViewModel.getSharedState()?.unit ?: "No unit",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface,
//                    )
//                }
                Spacer(modifier = Modifier.height(16.dp))
                // Start date
                Text(
                    text = viewModel.getViewModel().startDateTimestamp.toLocalDateTime()
                        .format(
                            DateTimeFormatter.ISO_DATE
                        ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // End date
                Text(
                    text = viewModel.getViewModel().endDateTimestamp.toLocalDateTime()
                        .format(
                            DateTimeFormatter.ISO_DATE
                        ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Days
                Text(
                    text = returnDayStringByBitMasking(sharedViewModel.getSharedState()?.days ?: 0),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
//                if (
//                    sharedViewModel.getSharedState()!!.planType == PlanType.RANGE
//                ) {
//                    // Start range
//                    Text(
//                        text = sharedViewModel.getSharedState()!!.startRange.toString(),
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    // End range
//                    Text(
//                        text = sharedViewModel.getSharedState()!!.endRange.toString(),
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                }
            }
        }
    }
}