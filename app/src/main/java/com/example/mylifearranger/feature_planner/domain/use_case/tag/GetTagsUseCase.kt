package com.example.mylifearranger.feature_planner.domain.use_case.tag

import com.example.mylifearranger.feature_planner.domain.model.Tag
import com.example.mylifearranger.feature_planner.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class GetTagsUseCase(
    private val tagRepository: TagRepository
) {

    operator fun invoke(): Flow<List<Tag>> {
        return tagRepository.getTags()
    }
}