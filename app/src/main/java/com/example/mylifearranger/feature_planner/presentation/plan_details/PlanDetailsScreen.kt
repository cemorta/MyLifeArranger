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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanDetailsScreen(
    navController: NavController,
    viewModel: PlanDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppBar(
                title = state.plan?.title ?: "",
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

            Column {

                Text(text = "Title: ${state.plan?.title}")
                Text(text = "Plan Type: ${state.plan?.planType}")
                Text(text = "Total Amount: ${state.plan?.totalAmount}")
                Text(text = "Completed Amount: ${state.plan?.completedAmount}")
                Text(text = "Unit: ${state.plan?.unit}")
                Text(text = "Start Range: ${state.plan?.startRange}")
                Text(text = "End Range: ${state.plan?.endRange}")
                Text(text = "Days: ${state.plan?.days}")
                if (state.plan?.startDateTimestamp != null) {
                    Text(text = "Start Date: ${state.plan?.startDateTimestamp!!.toLocalDateTime()}")
                }
                if (state.plan?.endDateTimestamp != null) {
                    Text(text = "End Date: ${state.plan?.endDateTimestamp!!.toLocalDateTime()}")
                }
                Text(text = "Is Done: ${state.plan?.isDone}")
            }
        }
    }
}