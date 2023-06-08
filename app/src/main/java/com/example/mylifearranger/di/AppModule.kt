package com.example.mylifearranger.di

import android.app.Application
import androidx.room.Room
import com.example.mylifearranger.feature_planner.data.data_source.AppDatabase
import com.example.mylifearranger.feature_planner.data.repository.EventRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.PlanRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.TaskRepositoryImpl
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import com.example.mylifearranger.feature_planner.domain.use_case.AddEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.DeleteEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.EventUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.GetEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.GetEventsForDateUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.GetEventsUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanWithTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.DeletePlanUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.GetPlanWithTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.task.AddTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.DeleteTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetMonthlyTasksForMonthUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTasksForDateUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetYearlyTasksForYearUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
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
            getEventsForDateUseCase = GetEventsForDateUseCase(eventRepository),
        )
    }

    @Provides
    @Singleton
    fun provideTaskRepository(appDatabase: AppDatabase): TaskRepository {
        return TaskRepositoryImpl(appDatabase.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(taskRepository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasksUseCase = GetTasksUseCase(taskRepository),
            deleteTaskUseCase = DeleteTaskUseCase(taskRepository),
            addTaskUseCase = AddTaskUseCase(taskRepository),
            getTaskUseCase = GetTaskUseCase(taskRepository),
            getYearlyTasksForYearUseCase = GetYearlyTasksForYearUseCase(taskRepository),
            getMonthlyTasksForMonthUseCase = GetMonthlyTasksForMonthUseCase(taskRepository),
            getTasksForDateUseCase = GetTasksForDateUseCase(taskRepository),
        )
    }

    @Provides
    @Singleton
    fun providePlanRepository(appDatabase: AppDatabase): PlanRepository {
        return PlanRepositoryImpl(appDatabase.planDao)
    }

    @Provides
    @Singleton
    fun providePlanUseCases(planRepository: PlanRepository): PlanUseCases {
        return PlanUseCases(
            addPlanUseCase = AddPlanUseCase(planRepository),
            addPlanTaskUseCase = AddPlanTaskUseCase(planRepository),
            addPlanWithTasksUseCase = AddPlanWithTasksUseCase(planRepository),
            deletePlanUseCase = DeletePlanUseCase(planRepository),
            getPlanWithTasksUseCase = GetPlanWithTasksUseCase(planRepository),
        )
    }
}