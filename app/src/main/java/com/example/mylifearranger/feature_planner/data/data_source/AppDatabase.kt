package com.example.mylifearranger.feature_planner.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mylifearranger.feature_planner.data.data_source.dao.EventDao
import com.example.mylifearranger.feature_planner.data.data_source.dao.GoalDao
import com.example.mylifearranger.feature_planner.data.data_source.dao.PlanDao
import com.example.mylifearranger.feature_planner.data.data_source.dao.SubtaskDao
import com.example.mylifearranger.feature_planner.data.data_source.dao.TagDao
import com.example.mylifearranger.feature_planner.data.data_source.dao.TaskDao
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.model.Tag
import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.model.TaskTag
import com.example.mylifearranger.feature_planner.domain.util.Converters

@Database(
    entities = [
        Event::class,
        Task::class,
        Plan::class,
        PlanTask::class,
        Goal::class,
        Subtask::class,
        Tag::class,
        TaskTag::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val eventDao: EventDao
    abstract val taskDao: TaskDao
    abstract val planDao: PlanDao
    abstract val goalDao: GoalDao
    abstract val subtaskDao: SubtaskDao
    abstract val tagDao: TagDao

    companion object {
        const val DATABASE_NAME = "my_life_arranger_db"
    }
}