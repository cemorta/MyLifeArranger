package com.example.mylifearranger.feature_planner.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylifearranger.feature_planner.domain.model.Event

@Database(entities = [Event::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract val eventDao: EventDao

    companion object {
        const val DATABASE_NAME = "my_life_arranger_db"
    }
}