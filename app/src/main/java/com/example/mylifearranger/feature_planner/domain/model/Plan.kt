package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.PlanType

@Entity(
    indices = [Index(value = ["assignedGoalId"])],
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
    val totalAmount: Int? = null,
    val completedAmount: Int = 0,
    val unit: String,
    val startRange: Int? = null,
    val endRange: Int? = null,
    val days: Int,
    val startDateTimestamp: Long,
    var endDateTimestamp: Long,
    val isDone: Boolean = false,
    val assignedGoalId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
