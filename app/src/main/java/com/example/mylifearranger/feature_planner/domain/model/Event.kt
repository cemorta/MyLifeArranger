package com.example.mylifearranger.feature_planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Event(
    val title: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
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
