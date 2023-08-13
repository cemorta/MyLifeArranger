package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity
data class Goal(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
