package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["assignedTaskId"],
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
data class Subtask(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    val assignedTaskId: Int? = null,
    val assignedEventId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
