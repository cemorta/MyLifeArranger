package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {

    fun getTags(): Flow<List<Tag>>

//    suspend fun getTagById(id: Int): Tag?

    suspend fun insertTag(event: Tag)

    suspend fun deleteTag(event: Tag)
}