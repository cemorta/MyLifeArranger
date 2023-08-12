package com.example.mylifearranger.feature_planner.domain.use_case.tag

import com.example.mylifearranger.feature_planner.domain.model.Tag
import com.example.mylifearranger.feature_planner.domain.repository.TagRepository

class AddTagUseCase(
    private val tagRepository: TagRepository
) {

//    @Throws(InvalidTagException::class)
    suspend operator fun invoke(tag: Tag) {
//        if (tag.title.isBlank()) {
//            throw InvalidTagException("The title of the tag can't be empty.")
//        }
//        if (tag.startTimestamp > tag.endTimestamp) {
//            throw InvalidTagException("The start time of the tag can't be after the end time.")
//        }
//        if (tag.color == 0) {
//            throw InvalidTagException("The color of the tag can't be empty.")
//        }
//        if (tag.start.hour == tag.end) {
//            throw InvalidTagException("The start time of the tag can't be the same as the end time.")
//        }

        tagRepository.insertTag(tag)
    }
}