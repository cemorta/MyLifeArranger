package com.example.mylifearranger.feature_planner.presentation.plan_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.mylifearranger.core.presentation.util.returnDayStringByBitMasking
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.plan_overview.PlanOverviewViewModel
import toLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PlanOverviewContent(
    viewModel: PlanOverviewViewModel,
) {

    val plan = viewModel.plan
    var planDaysArray = viewModel.planDaysArray
    var planDayCount = viewModel.planDayCount

    Column {
        val daysOfWeek = androidx.compose.ui.platform.LocalContext.current.resources.getStringArray(
            com.example.mylifearranger.R.array.days_of_week
        )

        // Show Plan Name, Plan Days, Plan Total Amount, and Plan Unit
        if (plan?.planType == PlanType.TOTAL && plan?.totalAmount != null && plan?.unit != null) {

            val planDays = returnDayStringByBitMasking(plan?.days ?: 0, daysOfWeek)
            PlanDetailsCard(planName = plan?.title!!, planDays = planDays, totalAmount = plan?.totalAmount!!, unit = plan?.unit!!)
        }

        // Show One-Week Overview
        if (plan?.planType == PlanType.TOTAL && planDaysArray.value != null) {

            OneWeekOverviewCard(planDaysArray = planDaysArray.value!!)
        }

        // Show Start Date, End Date, and Plan Day Count
        if (plan?.startDateTimestamp != null && plan?.endDateTimestamp != null && planDayCount.value != null) {

            StartEndDatesOverviewCard(
                startDate = plan?.startDateTimestamp.toLocalDateTime().format(
                    DateTimeFormatter.ISO_DATE
                ), endDate = plan?.endDateTimestamp.toLocalDateTime().format(
                    DateTimeFormatter.ISO_DATE
                ), dayCount = planDayCount.value!!
            )
        }
    }
}
