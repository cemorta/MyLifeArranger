package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["assignedGoalId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    val title: String,
    val duration: Long?, // Duration in seconds
    val taskType: TaskType,
    val plannedTimestamp: Long,
    val setPlannedTime: Boolean,
    val isDone: Boolean,
    val dueTimestamp: Long?,
    val assignedGoalId: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
