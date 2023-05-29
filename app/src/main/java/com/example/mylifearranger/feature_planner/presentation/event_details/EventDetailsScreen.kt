package com.example.mylifearranger.feature_planner.presentation.event_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.feature_planner.presentation.event_details.components.eventDetailsActionButtons
import com.example.mylifearranger.feature_planner.presentation.util.AppBar
import com.example.mylifearranger.feature_planner.presentation.util.BottomBar
import com.example.mylifearranger.feature_planner.presentation.util.BottomBarItem
import toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    navController: NavController,
    viewModel: EventDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppBar(
                title = state.event?.title ?: "",
                isThereBackButton = true,
                actionIconButtons = eventDetailsActionButtons({}, {
                    viewModel.onEvent(EventDetailsEvent.DeleteEvent(state.event!!))
                    navController.popBackStack()
                }),
                navController = navController
            )
        },
        bottomBar = {
            BottomBar()
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

                // Start date and time
                Text(
                    text = "Start date and time: ${state.event?.startTimestamp?.toLocalDateTime()}",
                    style = MaterialTheme.typography.displayMedium
                )
                // End date and time
                Text(
                    text = "End date and time: ${state.event?.endTimestamp?.toLocalDateTime()}",
                    style = MaterialTheme.typography.displayMedium
                )
                // Color
                Text(
                    text = "Color",
                    style = MaterialTheme.typography.displayMedium
                )
                // Description
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}