package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toLocalDateTime
import toTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PlanOverviewViewModel @Inject constructor(
    private val planUseCases: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var sharedViewModel: SharedViewModel
    var plan: Plan? = null
    val planTasks: MutableList<PlanTask> = mutableListOf()
//    var eachPlanDayAmount: Int? = null

    fun setViewModel(sharedViewModel: SharedViewModel) {
        this.sharedViewModel = sharedViewModel
        plan = sharedViewModel.getSharedState() ?: null

        // Make calculations for the plan and store the results in the following variables
        calculatePlan(
            plan,
            _eachPlanDayAmount,
            _planDayCount,
            _planDaysArray,
            planTasks,
            { sharedViewModel.setEndDateTimestamp(it) }) {
            sharedViewModel.setTotalAmount(it)
        }
    }

    fun getViewModel(): Plan? {
        plan = sharedViewModel.getSharedState() ?: Plan(
            title = "",
            planType = PlanType.TOTAL,
            days = 0,
            totalAmount = 0,
            unit = "",
            startRange = 0,
            endRange = 0,
            startDateTimestamp = LocalDateTime.now().toTimestamp(),
            endDateTimestamp = LocalDateTime.now().toTimestamp(),
        )
        return plan
    }

    private val _eachPlanDayAmount = mutableStateOf<Int?>(null)
    val eachPlanDayAmount: State<Int?> = _eachPlanDayAmount

    private val _planDayCount = mutableStateOf<Int?>(null)
    val planDayCount: State<Int?> = _planDayCount

    private val _planDaysArray = mutableStateOf<Array<Int>?>(null)
    val planDaysArray: State<Array<Int>?> = _planDaysArray

    private val _eventFlow = MutableSharedFlow<UiAction>()
    val eventFlow = _eventFlow.asSharedFlow()


    //
//    private var currentPlanId: Long? = null
//
//    init {
//        savedStateHandle.get<Int>("planId")?.let { planId ->
//            if (planId != -1) {
//                viewModelScope.launch {
//                    planUseCases.getPlanUseCase(planId)?.also { plan ->
//                        currentPlanId = plan.id
//                        _planTitle.value = planTitle.value.copy(
//                            text = plan.title,
//                            isHintVisible = false
//                        )
//                        _planType.value = plan.planType
//                        _days.value = plan.days
//                        _totalAmount.value = plan.totalAmount
//                        _unit.value = unit.value.copy(
//                            text = plan.unit,
//                            isHintVisible = false
//                        )
//                        _startRange.value = plan.startRange
//                        _endRange.value = plan.endRange
//                        _startDateTimestamp.value = plan.startDateTimestamp
//                        _endDateTimestamp.value = plan.endDateTimestamp
//                    }
//                }
//            }
//        }
//    }
//
    fun onAction(action: PlanOverviewAction) {
        when (action) {
            is PlanOverviewAction.SavePlan -> {
                viewModelScope.launch {
                    try {
                        planUseCases.addPlanWithTasksUseCase(
                            sharedViewModel.getSharedState()!!,
                            planTasks
                        )
                        sharedViewModel.clearSharedState()
                        _eventFlow.emit(UiAction.SavePlan)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiAction.ShowSnackbar(
                                message = e.message ?: "Couldn't save plan"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiAction {
        data class ShowSnackbar(val message: String) : UiAction()
        object SavePlan : UiAction()
    }
}

fun calculatePlan(
    plan: Plan?,
    stateEachPlanDayAmount: MutableState<Int?>,
    statePlanDayCount: MutableState<Int?>,
    statePlanDaysArray: MutableState<Array<Int>?>,
    planTasks: MutableList<PlanTask>,
    changeEndDateTimestamp: (Long) -> Unit,
    changeTotalAmount: (Int) -> Unit
) {

    var planDaysArray: Array<Int>? = null;
    var planDaysCount: Int? = null;

    // Validate if the start date and end date are not null
    if (plan?.startDateTimestamp != null && plan?.endDateTimestamp != null) {

        // Create an array of 7 elements and initialize it to -1
        // -1 means that the day is not included in the plan
        planDaysArray = arrayOf(-1, -1, -1, -1, -1, -1, -1)

        // Change the -1 values of the array of planDaysArray to 0 if the day is included in the plan
        changePlanDaysArrayByPlanDays(
            planDaysArray,
            plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
            plan?.endDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
            plan?.days ?: 0
        )

        // Get the number of plan days between the start date and end date
        planDaysCount = returnCountOfPlanDaysBetweenTwoLocalDateTimes(
            plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
            plan?.endDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
            planDaysArray
        )

        // Store the number of plan days between the start date and end date
        statePlanDayCount.value = planDaysCount
    }

    // If it is a range plan, do the following:
    if (plan?.planType == PlanType.RANGE && planDaysArray != null && planDaysCount != null) {
        // ----------------------- Range plan -----------------------------
        // Check if the start range and end range are not null by let keyword
        plan?.startRange?.let { startRange ->
            plan?.endRange?.let { endRange ->

                // The end range must be greater or equal to start range
                if (startRange <= endRange) {

                    // The total amount is the difference between the end range and start range + 1
                    // Modify the total amount of the plan
                    changeTotalAmount(endRange - startRange + 1)
                }
            }
        }
    }

    if (planDaysArray != null && planDaysCount != null) {

        // ----------------------- Total amount of work and distribution of work -----------------------------

        // Check if the total amount is not null by let keyword
        plan?.totalAmount?.let { totalAmount ->

            // If the total amount is greater than or equal to the number of plan days between the start date and end date
            if (totalAmount >= planDaysCount) {

                // Check if the total amount is divisible by the number of plan days between the start date and end date
                if (totalAmount % planDaysCount == 0) {

                    // If it is divisible, then distribute the work equally

                    // Get work amount per each plan day (totalAmount / planDaysCount)
                    stateEachPlanDayAmount.value = totalAmount / planDaysCount
                    // Change the 0 values of the array of planDaysArray to (eachPlanDayAmount)
                    for (i in 0..6) {
                        if (planDaysArray[i] == 0) {
                            planDaysArray[i] = stateEachPlanDayAmount.value!!
                        }
                    }
                    statePlanDaysArray.value = planDaysArray

                    createPlanTasks(
                        plan,
                        planDaysCount,
                        planDaysArray,
                        stateEachPlanDayAmount.value!!,
                        planTasks
                    )

                } else {

                    // If it is not divisible, then distribute the work equally and add the remainder to the first days

                    // Get approx. value of each plan day (totalAmount / planDaysCount)
                    stateEachPlanDayAmount.value = totalAmount / planDaysCount
                    // Get the remainder of (totalAmount / planDaysCount)
                    var remainder = totalAmount % planDaysCount
                    // Change the 0 values of the array of planDaysArray to (eachPlanDayAmount + 1) until the remainder is 0
                    for (i in 0..6) {
                        if (planDaysArray[i] == 0) {
                            if (remainder > 0) {
                                planDaysArray[i] = stateEachPlanDayAmount.value!! + 1
                                remainder--
                            } else {
                                planDaysArray[i] = stateEachPlanDayAmount.value!!
                            }
                        }
                    }
                    statePlanDaysArray.value = planDaysArray

                    createPlanTasks(
                        plan,
                        planDaysCount,
                        planDaysArray,
                        stateEachPlanDayAmount.value!!,
                        planTasks,
                        totalAmount % planDaysCount
                    )
                }

            } else {

                // If the total amount is less than the number of plan days, the work amount per day would be less than 1.
                // So, we need to change the end day to make the work amount per day equal to 1.

                // planDaysCount > totalAmount
                val newEndDate = returnMaxEndDateByTotalAmount(
                    plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                    totalAmount,
                    planDaysArray
                )
                changeEndDateTimestamp(
                    LocalDateTime.of(
                        newEndDate,
                        plan?.endDateTimestamp?.toLocalDateTime()?.toLocalTime()!!
                    ).toTimestamp()
                )
                createPlanTasks(
                    plan,
                    totalAmount,
                    planDaysArray,
                    1,
                    planTasks
                )

//                    changeEndDateTimestamp(LocalDateTime.of(newEndDate, plan?.endDateTimestamp?.toLocalDateTime()?.toLocalTime()!!).toTimestamp())

//                    Text(newEndDate.toString())
//                    ClickableWeekDayList(dayAmount = planDaysArray)

            }
        }
    }
}

fun createPlanTasks(
    plan: Plan,
    planDaysCount: Int,
    planDaysArray: Array<Int>,
    value: Int,
    planTasks: MutableList<PlanTask>,
    remainder: Int = 0
) {
    var workDay: LocalDate = plan.startDateTimestamp.toLocalDateTime().toLocalDate()
    // Create PlanTask objects for each plan day
    for (i in 0 until planDaysCount) {
        workDay = returnNextWorkDay(
            workDay,
            planDaysArray
        )

        if (remainder - i > 0) {
            planTasks.add(
                PlanTask(
                    title = "",
                    description = "",
                    amountToComplete = value + 1,
                    taskDuration = null,
                    performedDateTimestamp = workDay.atStartOfDay().toTimestamp(),
                    setPlannedTime = false,
                    isDone = false,
                    assignedPlanId = -1, // assignedPlanId is not assigned, it will assigned after the plan is saved
                    assignedEventId = null
                )
            )
        } else {
            planTasks.add(
                PlanTask(
                    title = "",
                    description = "",
                    amountToComplete = value,
                    taskDuration = null,
                    performedDateTimestamp = workDay.atStartOfDay().toTimestamp(),
                    setPlannedTime = false,
                    isDone = false,
                    assignedPlanId = -1, // assignedPlanId is not assigned, it will assigned after the plan is saved
                    assignedEventId = null
                )
            )
        }
        // 1 day added to the workDay
        workDay = workDay.plusDays(1)
    }
}

// Function to changes the -1 values of the array of planDaysArray to 0 if the day is included in the plan
fun changePlanDaysArrayByPlanDays(
    planDaysArray: Array<Int>,
    startDate: LocalDate,
    endDate: LocalDate,
    days: Int
) {
    var tempDate = startDate
    for (i in 1..7) {
        if (days.and(1 shl (tempDate.dayOfWeek.value - 1)) == 1 shl (tempDate.dayOfWeek.value - 1) && !tempDate.isAfter(
                endDate
            )
        ) {
            planDaysArray[tempDate.dayOfWeek.value - 1] = 0
        }
        tempDate = tempDate.plusDays(1)
    }
}

fun returnNextWorkDay(
    startDate: LocalDate,
    planDaysArray: Array<Int>
): LocalDate {
    var tempDate = startDate
    while (planDaysArray[tempDate.dayOfWeek.value - 1] < 1) {
        tempDate = tempDate.plusDays(1)
    }
    return tempDate
}

fun returnCountOfPlanDaysBetweenTwoLocalDateTimes(
    startDate: LocalDate,
    endDate: LocalDate,
    planDaysArray: Array<Int>
): Int {
    var count = 0
    var tempDate = startDate
    while (!tempDate.isAfter(endDate)) {

        if (planDaysArray[tempDate.dayOfWeek.value - 1] == 0) {
            count++
        }
        tempDate = tempDate.plusDays(1)
    }
    return count
}

fun returnMaxEndDateByTotalAmount(
    startDate: LocalDate,
    totalAmount: Int,
    planDaysArray: Array<Int>
): LocalDate {
    var tempDate = startDate
    var count = 0
    while (count < totalAmount) {

        if (planDaysArray[tempDate.dayOfWeek.value - 1] != -1) {
            count++
            // if planDaysArray is not 1 make 1
            if (planDaysArray[tempDate.dayOfWeek.value - 1] == 0) {
                planDaysArray[tempDate.dayOfWeek.value - 1] = 1
            }
        }
        tempDate = tempDate.plusDays(1)
    }
    return tempDate.minusDays(1)
}
