package com.example.mylifearranger.di

import android.app.Application
import androidx.room.Room
import com.example.mylifearranger.feature_planner.data.data_source.AppDatabase
import com.example.mylifearranger.feature_planner.data.repository.EventRepositoryImpl
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import com.example.mylifearranger.feature_planner.domain.use_case.AddEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.DeleteEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.EventUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.GetEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.GetEventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventRepository(appDatabase: AppDatabase): EventRepository {
        return EventRepositoryImpl(appDatabase.eventDao)
    }

    @Provides
    @Singleton
    fun provideEventUseCases(eventRepository: EventRepository): EventUseCases {
        return EventUseCases(
            getEventsUseCase = GetEventsUseCase(eventRepository),
            deleteEventUseCase = DeleteEventUseCase(eventRepository),
            addEventUseCase = AddEventUseCase(eventRepository),
            getEventUseCase = GetEventUseCase(eventRepository),
        )
    }
}