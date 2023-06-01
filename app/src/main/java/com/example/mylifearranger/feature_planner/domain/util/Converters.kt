package com.example.mylifearranger.feature_planner.domain.util

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toTaskType(value: String) = enumValueOf<TaskType>(value)

    @TypeConverter
    fun fromTaskType(value: TaskType) = value.name
}
