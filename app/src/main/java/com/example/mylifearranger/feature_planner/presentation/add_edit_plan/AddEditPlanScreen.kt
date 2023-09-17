package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.DatePicker
import com.example.mylifearranger.core.presentation.components.TransparentHintTextField
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.components.ClickableRectangle
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPlanScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: AddEditPlanViewModel = hiltViewModel(),
) {
    viewModel.setViewModel(sharedViewModel)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditPlanViewModel.UiAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditPlanViewModel.UiAction.SavePlan -> {
                    navController.navigate(Screen.PlanOverviewScreen.route)
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditPlanAction.SavePlan)
                },
                Modifier.background(
                    MaterialTheme.colorScheme.background,
                )
            ) {
                // TODO: change icon if it is edit mode
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
                    contentDescription = "Continue creating plan",
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        topBar = {
            AppBar(
                title = "Add Plan",
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

            Column {

                // Title
                TransparentHintTextField(
                    text = viewModel.planTitle.value.text,
                    hint = viewModel.planTitle.value.hint,
                    onValueChange = { viewModel.onEvent(AddEditPlanAction.EnteredTitle(it)) },
                    onFocusChange = { viewModel.onEvent(AddEditPlanAction.ChangeTitleFocus(it)) },
                    isHintVisible = true,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Start date and End date
                DatePicker(
                    label = "start",
                    value = viewModel.startDateTimestamp.value.toLocalDateTime().toLocalDate().format(
                        DateTimeFormatter.ISO_DATE
                    ),
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditPlanAction.EnteredStartDate(
                                LocalDate.parse(
                                    it,
                                    DateTimeFormatter.ISO_DATE
                                )
                            )
                        )
                    }
                )
                DatePicker(
                    label = "end",
                    value = viewModel.endDateTimestamp.value.toLocalDateTime().toLocalDate().format(
                        DateTimeFormatter.ISO_DATE
                    ),
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditPlanAction.EnteredEndDate(
                                LocalDate.parse(
                                    it,
                                    DateTimeFormatter.ISO_DATE
                                )
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                var width by remember { mutableStateOf(0) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .onSizeChanged { size -> width = size.width }
                ) {

                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ClickableRectangle(
                                text = "Total",
                                if (viewModel.planType.value == PlanType.TOTAL) Color.LightGray else Color.White,
                                modifier = Modifier.weight(1f)
                            ) {
                                viewModel.onEvent(AddEditPlanAction.ChangePlanType(PlanType.TOTAL))
                            }
                            ClickableRectangle(
                                text = "Range",
                                if (viewModel.planType.value == PlanType.RANGE) Color.LightGray else Color.White,
                                modifier = Modifier.weight(1f),
                            ) {
                                viewModel.onEvent(AddEditPlanAction.ChangePlanType(PlanType.RANGE))
                            }

                        }
                        if (viewModel.planType.value == PlanType.TOTAL) {
                            Row() {

                                // Text input with number
                                TransparentHintTextField(
                                    text = viewModel.totalAmount.value?.toString() ?: "",
                                    hint = "Total",
                                    onValueChange = {
                                        viewModel.onEvent(
                                            AddEditPlanAction.EnteredTotalAmount(
                                                it
                                            )
                                        )
                                    },
                                    onFocusChange = {},
                                    isHintVisible = false,
                                    modifier = Modifier.weight(2f),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium,
                                )
                                // Text input for unit
                                TransparentHintTextField(
                                    text = viewModel.unit.value.text,
                                    hint = viewModel.unit.value.hint,
                                    onValueChange = {},
                                    onFocusChange = {},
                                    isHintVisible = true,
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        } else {
                            Row(horizontalArrangement = Arrangement.SpaceAround) {
                                // Text input with number start range
                                TransparentHintTextField(
                                    text = "start range",
                                    hint = "start range",
                                    onValueChange = {},
                                    onFocusChange = {},
                                    isHintVisible = true,
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium,
                                )
                                // Text input with number end range
                                TransparentHintTextField(
                                    text = "end range",
                                    hint = "end range",
                                    onValueChange = {},
                                    onFocusChange = {},
                                    isHintVisible = true,
                                    modifier = Modifier.weight(1f),
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
//                var days = remember {
//                    mutableStateListOf(
//                        false, false, false, false, false, false, false
//                    )
//                }
                // Day picker
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val daysOfWeek = androidx.compose.ui.platform.LocalContext.current.resources.getStringArray(R.array.days_of_week)
                    ClickableRectangle(
                        text = daysOfWeek[0],
                        if (viewModel.days.value and 1 == 1) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(1))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[1],
                        if (viewModel.days.value and 2 == 2) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(2))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[2],
                        if (viewModel.days.value and 4 == 4) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(4))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[3],
                        if (viewModel.days.value and 8 == 8) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(8))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[4],
                        if (viewModel.days.value and 16 == 16) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(16))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[5],
                        if (viewModel.days.value and 32 == 32) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(32))
                    }
                    ClickableRectangle(
                        text = daysOfWeek[6],
                        if (viewModel.days.value and 64 == 64) Color.LightGray else Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.onEvent(AddEditPlanAction.SwitchDays(64))
                    }
                }
            }

        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddEditPlanScreenPreview() {
//
//
//}