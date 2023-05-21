package com.example.mylifearranger.feature_planner.domain.model

data class Todo(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val isDone: Boolean
)
