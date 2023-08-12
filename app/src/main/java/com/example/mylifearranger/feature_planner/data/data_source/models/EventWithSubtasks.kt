package com.example.mylifearranger.feature_planner.data.data_source.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.Subtask

data class EventWithSubtasks(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "id",
        entityColumn = "assignedEventId"
    )
    val subtasks: List<Subtask>
)
