package com.example.mylifearranger.di

import android.app.Application
import androidx.room.Room
import com.example.mylifearranger.feature_planner.data.data_source.AppDatabase
import com.example.mylifearranger.feature_planner.data.repository.EventRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.GoalRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.PlanRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.SubtaskRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.TagRepositoryImpl
import com.example.mylifearranger.feature_planner.data.repository.TaskRepositoryImpl
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import com.example.mylifearranger.feature_planner.domain.repository.SubtaskRepository
import com.example.mylifearranger.feature_planner.domain.repository.TagRepository
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import com.example.mylifearranger.feature_planner.domain.use_case.event.AddEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.event.DeleteEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.event.EventUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.event.GetEventUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.event.GetEventsForDateUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.event.GetEventsUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.goal.AddGoalUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.goal.DeleteGoalUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.goal.GetGoalUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.goal.GetGoalsUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.goal.GoalUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.AddPlanWithTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.DeletePlanUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.GetPlanTasksBetweenTwoDatesUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.GetPlanUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.GetPlanWithTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.GetPlansUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.plan.UpdatePlanCompletedAmountUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.plan.UpdatePlanIsDoneUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.AddSubtaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.AddSubtasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.DeleteSubtaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.DeleteSubtasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.GetSubtasksForEventIdUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.GetSubtasksForTaskIdUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.subtask.SubtaskUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.tag.AddTagUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.tag.DeleteTagUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.tag.GetTagsUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.tag.TagUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.task.AddTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.DeleteTaskUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetMonthlyTasksForMonthUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetNoneTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetDailyTasksForDateUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTaskWithSubtasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTasksByCompletionUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetTasksUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.GetYearlyTasksForYearUseCase
import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.task.UpdateTaskCompletionUseCase
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
            getTasksByCompletionUseCase = GetTasksByCompletionUseCase(taskRepository),
            deleteTaskUseCase = DeleteTaskUseCase(taskRepository),
            addTaskUseCase = AddTaskUseCase(taskRepository),
            getTaskUseCase = GetTaskWithSubtasksUseCase(taskRepository),
            getYearlyTasksForYearUseCase = GetYearlyTasksForYearUseCase(taskRepository),
            getMonthlyTasksForMonthUseCase = GetMonthlyTasksForMonthUseCase(taskRepository),
            getNoneTasksUseCase = GetNoneTasksUseCase(taskRepository),
            getDailyTasksForDateUseCase = GetDailyTasksForDateUseCase(taskRepository),
            updateTaskCompletionUseCase = UpdateTaskCompletionUseCase(taskRepository),
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
            getPlansUseCase = GetPlansUseCase(planRepository),
            getPlanWithTasksUseCase = GetPlanWithTasksUseCase(planRepository),
            getPlanTasksBetweenTwoDatesUseCase = GetPlanTasksBetweenTwoDatesUseCase(planRepository),
            getPlanUseCase = GetPlanUseCase(planRepository),
            updatePlanCompletedAmountUseCase = UpdatePlanCompletedAmountUseCase(planRepository),
            updatePlanIsDoneUseCase = UpdatePlanIsDoneUseCase(planRepository)
        )
    }

    @Provides
    @Singleton
    fun provideGoalRepository(appDatabase: AppDatabase): GoalRepository {
        return GoalRepositoryImpl(appDatabase.goalDao)
    }

    @Provides
    @Singleton
    fun provideGoalUseCases(goalRepository: GoalRepository): GoalUseCases {
        return GoalUseCases(
            addGoalUseCase = AddGoalUseCase(goalRepository),
            deleteGoalUseCase = DeleteGoalUseCase(goalRepository),
            getGoalsUseCase = GetGoalsUseCase(goalRepository),
            getGoalUseCase = GetGoalUseCase(goalRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSubtaskRepository(appDatabase: AppDatabase): SubtaskRepository {
        return SubtaskRepositoryImpl(appDatabase.subtaskDao)
    }

    @Provides
    @Singleton
    fun provideSubtaskUseCases(subtaskRepository: SubtaskRepository): SubtaskUseCases {
        return SubtaskUseCases(
            addSubtaskUseCase = AddSubtaskUseCase(subtaskRepository),
            addSubtasksUseCase = AddSubtasksUseCase(subtaskRepository),
            deleteSubtaskUseCase = DeleteSubtaskUseCase(subtaskRepository),
            deleteSubtasksUseCase = DeleteSubtasksUseCase(subtaskRepository),
            getSubtasksForEventIdUseCase = GetSubtasksForEventIdUseCase(subtaskRepository),
            getSubtasksForTaskIdUseCase = GetSubtasksForTaskIdUseCase(subtaskRepository),
        )
    }

    @Provides
    @Singleton
    fun provideTagRepository(appDatabase: AppDatabase): TagRepository {
        return TagRepositoryImpl(appDatabase.tagDao)
    }

    @Provides
    @Singleton
    fun provideTagUseCases(tagRepository: TagRepository): TagUseCases {
        return TagUseCases(
            addTagUseCase = AddTagUseCase(tagRepository),
            deleteTagUseCase = DeleteTagUseCase(tagRepository),
            getTagsUseCase = GetTagsUseCase(tagRepository),
        )
    }
}