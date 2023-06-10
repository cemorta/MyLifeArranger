package com.example.mylifearranger.feature_planner.presentation.plan_view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.core.presentation.util.returnDayStringByBitMasking
import toLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PlanInfoRow(plan: Plan) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {

            Text(text = plan.title, fontWeight = FontWeight.Bold)
            Text(text = returnDayStringByBitMasking(plan.days))
//            Text(text = plan.planType.toString())
        }
        Column(horizontalAlignment = Alignment.End) {

            // Show start and end date
            Text(
                text = "${
                    plan.startDateTimestamp.toLocalDateTime().format(DateTimeFormatter.ISO_DATE)
                } - ${plan.endDateTimestamp.toLocalDateTime().format(DateTimeFormatter.ISO_DATE)}"
            )

            when (plan.planType) {
                PlanType.TOTAL -> {
                    Text(text = "${plan.totalAmount} ${plan.unit}")
                }

                else -> {
                    Text(text = "${plan.startRange} - ${plan.endRange} ${plan.unit}")
                }
            }
        }
    }
}