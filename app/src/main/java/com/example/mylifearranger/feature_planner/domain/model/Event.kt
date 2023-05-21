package com.example.mylifearranger.feature_planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.PrimaryKey
import java.time.LocalTime

data class Event(
    val title: String,
    val start: LocalTime,
    val end: LocalTime,
    val color: Int,
    val isDone: Boolean,
    val isAllDay: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    companion object {
        val eventColors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Red, Color.Yellow)
    }
}
