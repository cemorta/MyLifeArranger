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
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlanTask(
    @ColumnInfo(index = true)
    val planId: Int,
    val title: String,
    val description: String,
    val amountToDo: Int,
    val startDateTimestamp: Long,
    val setStartTime: Boolean,
    val endDateTimestamp: Long? = null,
    val setEndTime: Boolean = false,
    val isDone: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
