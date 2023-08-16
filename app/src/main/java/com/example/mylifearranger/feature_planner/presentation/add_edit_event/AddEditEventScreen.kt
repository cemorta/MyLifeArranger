package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.components.ColorSelectionRow
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.components.FormContainer
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditEventScreen(
    navController: NavController,
    viewModel: AddEditEventViewModel = hiltViewModel()
) {
    val titleState = viewModel.eventTitle.value
    val startDateState = viewModel.eventStartDateTime.value
    val endDateState = viewModel.eventEndDateTime.value
    val colorState = viewModel.eventColor.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditEventViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditEventViewModel.UiEvent.SaveEvent -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditEventAction.SaveEvent)
                },
                Modifier.background(
                    MaterialTheme.colorScheme.background,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save event"
                )
            }
        },
        topBar = {
            AppBar(
                title = stringResource(id = R.string.add_event),
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

                ColorSelectionRow(eventColorValue = colorState) {
                    viewModel.onEvent(AddEditEventAction.ChangeColor(it))
                }
                Spacer(modifier = Modifier.height(16.dp))
                FormContainer(
                    titleState = titleState,
                    startDateState = startDateState,
                    endDateState = endDateState,
                    onTitleChange = { viewModel.onEvent(AddEditEventAction.EnteredTitle(it)) },
                    onTitleFocusChange = { viewModel.onEvent(AddEditEventAction.ChangeTitleFocus(it)) },
                    onStartDateSelected = { viewModel.onEvent(AddEditEventAction.EnteredStartDate(it)) },
                    onStartTimeSelected = { viewModel.onEvent(AddEditEventAction.EnteredStartTime(it)) },
                    onEndDateSelected = { viewModel.onEvent(AddEditEventAction.EnteredEndDate(it)) },
                    onEndTimeSelected = { viewModel.onEvent(AddEditEventAction.EnteredEndTime(it)) },
                )
            }
        }
    }
}
