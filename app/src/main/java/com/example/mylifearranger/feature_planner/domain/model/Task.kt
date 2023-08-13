package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity(
    indices = [Index(value = ["assignedGoalId"]), Index(value = ["assignedEventId"])],
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["assignedGoalId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Event::class,
            parentColumns = ["id"],
            childColumns = ["assignedEventId"],
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
    val assignedGoalId: Int? = null,
    val assignedEventId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
