package com.example.mylifearranger.core.presentation.util

import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.util.TaskType

fun TaskType.toResId(): Int {
    return when (this) {
        TaskType.DAILY -> R.string.daily
        TaskType.MONTHLY -> R.string.monthly
        TaskType.YEARLY -> R.string.yearly
        TaskType.NONE -> R.string.none
    }
}
