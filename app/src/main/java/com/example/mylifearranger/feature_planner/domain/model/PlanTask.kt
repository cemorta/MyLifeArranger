package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Plan::class,
            parentColumns = ["id"],
            childColumns = ["assignedPlanId"],
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
data class PlanTask(
    @ColumnInfo(index = true)
    val title: String,
    val description: String?,
    val amountToComplete: Int,
    val taskDuration: Long?,
    val performedDateTimestamp: Long,
    val setPlannedTime: Boolean,
    val isDone: Boolean = false,
    val assignedPlanId: Int,
    val assignedEventId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
