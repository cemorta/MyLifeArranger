package com.example.mylifearranger.feature_planner.presentation.plan_view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.domain.model.Plan

@Composable
fun PlanList(plans: List<Plan>) {

    LazyColumn {
        items(plans.size) { index ->
            PlanInfoRow(plan = plans[index])
        }
    }
}