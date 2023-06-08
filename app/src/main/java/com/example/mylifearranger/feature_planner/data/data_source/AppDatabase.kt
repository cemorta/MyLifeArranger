package com.example.mylifearranger.feature_planner.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.util.Converters

@Database(
    entities = [
        Event::class,
        Task::class,
        Plan::class,
        PlanTask::class,
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val eventDao: EventDao
    abstract val taskDao: TaskDao
    abstract val planDao: PlanDao

    companion object {
        const val DATABASE_NAME = "my_life_arranger_db"
    }
}