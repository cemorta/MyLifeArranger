package com.example.mylifearranger.feature_planner.data.data_source.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.Task

data class EventWithTask(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "assignedTaskId",
        entityColumn = "id"
    )
    val task: Task?
)
