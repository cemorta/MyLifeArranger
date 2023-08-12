package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.TagDao
import com.example.mylifearranger.feature_planner.domain.model.Tag
import com.example.mylifearranger.feature_planner.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class TagRepositoryImpl(
    private val tagDao: TagDao
) : TagRepository {
    override fun getTags(): Flow<List<Tag>> {
        return tagDao.getTags()
    }

    override suspend fun insertTag(tag: Tag) {
        return tagDao.insertTag(tag)
    }

    override suspend fun deleteTag(tag: Tag) {
        return tagDao.deleteTag(tag)
    }

}