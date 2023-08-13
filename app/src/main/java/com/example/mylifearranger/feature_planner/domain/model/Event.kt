package com.example.mylifearranger.feature_planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["assignedTaskId"]), Index(value = ["assignedPlanTaskId"])],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["assignedTaskId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlanTask::class,
            parentColumns = ["id"],
            childColumns = ["assignedPlanTaskId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Event(
    val title: String,
    val description: String? = null,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val color: Int,
    val isDone: Boolean = false,
    val isAllDay: Boolean = false,
    val iconResName: String? = null,
    val assignedTaskId: Int? = null,
    val assignedPlanTaskId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val eventColors =
            listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Red, Color.Yellow)
    }
}

class InvalidEventException(message: String) : Exception(message)
