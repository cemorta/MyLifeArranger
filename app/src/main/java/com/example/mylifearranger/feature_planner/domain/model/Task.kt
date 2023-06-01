package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity
data class Task(
    val title: String,
    val duration: Long?, // Duration in seconds
    val taskType: TaskType,
    val plannedTimestamp: Long,
    val setPlannedTime: Boolean,
    val isDone: Boolean,
    val dueTimestamp: Long?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
