package com.example.mylifearranger.feature_planner.domain.use_case

data class EventUseCases(
    val getEventsUseCase: GetEventsUseCase,
    val deleteEventUseCase: DeleteEventUseCase,
    val addEventUseCase: AddEventUseCase,
    val getEventUseCase: GetEventUseCase,
    val getEventsForDateUseCase: GetEventsForDateUseCase
)
