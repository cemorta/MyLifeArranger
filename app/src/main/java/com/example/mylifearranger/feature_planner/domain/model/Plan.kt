package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.PlanType

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
data class Plan(
    val title: String,
    val planType: PlanType,
    val totalAmount: Int?,
    val completedAmount: Int = 0,
    val unit: String,
    val startRange: Int?,
    val endRange: Int?,
    val days: Int,
    val startDateTimestamp: Long,
    val endDateTimestamp: Long,
    val isDone: Boolean = false,
    val assignedGoalId: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
