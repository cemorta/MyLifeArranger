package com.example.mylifearranger.feature_planner.presentation.event_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.feature_planner.presentation.event_details.components.eventDetailsActionButtons
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

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
                actionIconButtons = eventDetailsActionButtons({
                    navController.navigate(Screen.AddEditEventScreen.route + "?eventId=${state.event?.id}&eventColor=${state.event?.color}")
                }, {
                    viewModel.onEvent(EventDetailsAction.DeleteEvent(state.event!!))
                    navController.popBackStack()
                }),
                navController = navController
            )
        },
        bottomBar = {
            BottomBar(navController, BottomBarItem.items[0])
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
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    // Start date and time
                    Text(
                        text = "Start date and time", modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                    Column(modifier = Modifier.padding(20.dp, 10.dp, 10.dp, 10.dp)) {
                        Text(
                            text = "Start date: ${
                                state.event?.startTimestamp?.toLocalDateTime()?.dayOfMonth.toString() + " " +
                                        (state.event?.startTimestamp?.toLocalDateTime()?.month?.getDisplayName(
                                            TextStyle.FULL, Locale.getDefault()
                                        ) ?: "") + " " +
                                        state.event?.startTimestamp?.toLocalDateTime()?.year
                            }",
                        )
                        Text(
                            text = "Start time: ${
                                state.event?.startTimestamp?.toLocalDateTime()?.hour.toString() + ":" +
                                        state.event?.startTimestamp?.toLocalDateTime()?.minute.toString()
                            }",
                        )
                    }
                }

                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    // End date and time
                    Text(
                        text = "End date and time", modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                    Column(modifier = Modifier.padding(20.dp, 10.dp, 10.dp, 10.dp)) {
                        Text(
                            text = "End date: ${
                                state.event?.endTimestamp?.toLocalDateTime()?.dayOfMonth.toString() + " " +
                                        (state.event?.endTimestamp?.toLocalDateTime()?.month?.getDisplayName(
                                            TextStyle.FULL, Locale.getDefault()) ?: "") + " " +
                                        state.event?.endTimestamp?.toLocalDateTime()?.year}",
                        )
                        Text(
                            text = "End time: ${
                                state.event?.endTimestamp?.toLocalDateTime()?.hour.toString() + ":" +
                                        state.event?.endTimestamp?.toLocalDateTime()?.minute.toString()
                            }",
                        )
                    }
                }
            }
        }
    }
}