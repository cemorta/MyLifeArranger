package com.example.mylifearranger.feature_planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
data class Event(
    val title: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val color: Int,
    val isDone: Boolean = false,
    val isAllDay: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val eventColors =
            listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Red, Color.Yellow)
    }
}

class InvalidEventException(message: String) : Exception(message)
