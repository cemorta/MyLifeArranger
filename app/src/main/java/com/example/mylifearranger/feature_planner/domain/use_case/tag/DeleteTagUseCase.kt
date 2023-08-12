package com.example.mylifearranger.feature_planner.domain.use_case.tag

import com.example.mylifearranger.feature_planner.domain.model.Tag
import com.example.mylifearranger.feature_planner.domain.repository.TagRepository

class DeleteTagUseCase(
    private val tagRepository: TagRepository
) {

    suspend operator fun invoke(tag: Tag) {
        tagRepository.deleteTag(tag)
    }
}