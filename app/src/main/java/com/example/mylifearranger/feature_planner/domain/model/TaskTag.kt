package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Entity(
    primaryKeys = ["taskId", "tagId"],
    foreignKeys = [
        ForeignKey(entity = Task::class, parentColumns = ["id"], childColumns = ["taskId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Tag::class, parentColumns = ["id"], childColumns = ["tagId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class TaskTag(
    val taskId: Int,
    val tagId: Int,
)
