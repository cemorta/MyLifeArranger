package com.example.mylifearranger.feature_planner.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylifearranger.feature_planner.domain.model.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Query("SELECT * FROM event")
    fun getTags(): Flow<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(event: Tag)

    @Delete
    suspend fun deleteTag(event: Tag)
}