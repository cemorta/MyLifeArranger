package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity
data class Tag(
    val tagName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
