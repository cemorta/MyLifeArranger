package com.example.mylifearranger.feature_planner.presentation.plan_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mylifearranger.core.presentation.util.returnDayStringByBitMasking
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import toLocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PlanOverviewContent(
    state: SharedViewModel,
) {
    val plan = state.getSharedState()

    Column {
        // Show Plan Title
        Text(text = plan?.title ?: "")

        // Show Plan Start Date and End Date
        Row {

            Text(
                text = plan?.startDateTimestamp?.toLocalDateTime()
                    ?.format(DateTimeFormatter.ISO_DATE)
                    ?: ""
            )
            Text(
                text = plan?.endDateTimestamp?.toLocalDateTime()?.format(DateTimeFormatter.ISO_DATE)
                    ?: ""
            )
        }

        // If it is a total amount plan, show the following:
        // Show Plan Total Amount and Plan Unit
        Row {
            if (plan?.planType == PlanType.TOTAL) {

                Text(text = plan?.totalAmount.toString())
                Text(text = plan?.unit ?: "")
            } else {

                // If it is a range plan, show the following:
                // Show Plan Start Range and End Range
                Text(text = plan?.startRange.toString())
                Text(text = plan?.endRange.toString())
            }
        }

        val daysOfWeek = androidx.compose.ui.platform.LocalContext.current.resources.getStringArray(
            com.example.mylifearranger.R.array.days_of_week
        )
        // Show Plan Days
        Text(text = returnDayStringByBitMasking(plan?.days ?: 0, daysOfWeek))

        // If it is a total amount plan, do the following:
        if (plan?.planType == PlanType.TOTAL) {

            // Get the week day of the start date
            val startDateWeekDay =
                plan?.startDateTimestamp?.toLocalDateTime()?.dayOfWeek?.value ?: 0
            // Get the week day of the end date
            val endDateWeekDay = plan?.endDateTimestamp?.toLocalDateTime()?.dayOfWeek?.value ?: 0
            // Get the number of plan days between the start date and end date
            if (plan?.startDateTimestamp != null && plan?.endDateTimestamp != null) {

                // Create an array of 7 elements and initialize it to -1
                // -1 means that the day is not included in the plan
                // Change the -1 values of the array of planDaysArray to 0 if the day is included in the plan
                var planDaysArray: Array<Int> = arrayOf(-1, -1, -1, -1, -1, -1, -1)
                changePlanDaysArrayByPlanDays(
                    planDaysArray,
                    plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                    plan?.endDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                    plan?.days ?: 0
                )
//                for (i in 1..7) {
//                    if (plan?.days?.and(1 shl (i - 1)) == 1 shl (i - 1)) {
//                        planDaysArray[i - 1] = 0
//                    }
//                }

                val planDaysCount = returnCountOfPlanDaysBetweenTwoLocalDateTimes(
                    plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                    plan?.endDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                    planDaysArray
                )
                // Show the number of plan days between the start date and end date
                Text(text = planDaysCount.toString())

                plan?.totalAmount?.let { totalAmount ->
                    if (totalAmount >= planDaysCount) {

                        if (totalAmount % planDaysCount == 0) {

                            // Get value of each plan day (totalAmount / planDaysCount)
                            val eachPlanDayAmount = totalAmount / planDaysCount
                            // Change the 0 values of the array of planDaysArray to (eachPlanDayAmount)
                            for (i in 0..6) {
                                if (planDaysArray[i] == 0) {
                                    planDaysArray[i] = eachPlanDayAmount
                                }
                            }
                        } else {

//                          // Get approx. value of each plan day (totalAmount / planDaysCount)
                            val eachPlanDayAmount = totalAmount / planDaysCount
                            // Get the remainder of (totalAmount / planDaysCount)
                            var remainder = totalAmount % planDaysCount
                            // Change the 0 values of the array of planDaysArray to (eachPlanDayAmount + 1) until the remainder is 0
                            for (i in 0..6) {
                                if (planDaysArray[i] == 0) {
                                    if (remainder > 0) {
                                        planDaysArray[i] = eachPlanDayAmount + 1
                                        remainder--
                                    } else {
                                        planDaysArray[i] = eachPlanDayAmount
                                    }
                                }
                            }
                        }

                        ClickableWeekDayList(dayAmount = planDaysArray)

                    } else {

                        // planDaysCount > totalAmount
                        val newEndDate = returnMaxEndDateByTotalAmount(
                            plan?.startDateTimestamp?.toLocalDateTime()?.toLocalDate()!!,
                            totalAmount,
                            planDaysArray
                        )

                        Text(newEndDate.toString())
                        ClickableWeekDayList(dayAmount = planDaysArray)


                    }
                }
            }


        }


    }
}

fun changePlanDaysArrayByPlanDays(
    planDaysArray: Array<Int>,
    startDate: LocalDate,
    endDate: LocalDate,
    days: Int
) {
    var tempDate = startDate
    for (i in 1..7) {
        if (days.and(1 shl (i - 1)) == 1 shl (i - 1) && !tempDate.isAfter(endDate)) {
            planDaysArray[tempDate.dayOfWeek.value - 1] = 0
        }
    }
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