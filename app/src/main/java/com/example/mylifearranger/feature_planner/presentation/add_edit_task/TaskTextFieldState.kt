package com.example.mylifearranger.feature_planner.presentation.add_edit_task

data class TaskTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)