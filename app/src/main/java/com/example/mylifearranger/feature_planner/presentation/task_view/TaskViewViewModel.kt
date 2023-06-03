package com.example.mylifearranger.feature_planner.presentation.task_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TaskViewViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TaskViewState())
    val state: State<TaskViewState> = _state


    //    private var getTasksForDateJob: Job? = null
    private var getTasksJob: Job? = null

    init {
        val date = savedStateHandle.get<String>("date")
        state.value.date = date
        val taskType = savedStateHandle.get<String>("taskType")
        if (taskType != null) {
            state.value.taskType = TaskType.valueOf(taskType)
        }

        if (date != null && taskType != null) {
            println("TaskViewViewModel: init: date = $date, taskType = $taskType")
            getTasksForDateAndType(date, state.value.taskType)
        }
    }

    //
//    fun onTask(task: DayViewTask) {
//        when (task) {
//            is DayViewTask.DeleteTask -> {
//                viewModelScope.launch {
//                    taskUseCases.deleteTaskUseCase(task.task)
//                }
//            }
//        }
//    }
//
//    private fun getTasksForDate(date: String) {
//        println("DayViewViewModel: getTasksForDate: date = $date")
//        getTasksForDateJob?.cancel()
//        getTasksForDateJob = taskUseCases.getTasksForDateUseCase(date).onEach { tasks ->
//            _state.value = state.value.copy(tasks = tasks)
//        }.launchIn(viewModelScope)
//    }
//
    private fun getTasksForDateAndType(date: String, taskType: TaskType) {
        getTasksJob?.cancel()
        when (taskType) {
            TaskType.DAILY -> {
                getTasksJob = taskUseCases.getTasksForDateUseCase(date).onEach { tasks ->
                    _state.value = state.value.copy(tasks = tasks)
                }.launchIn(viewModelScope)
            }

            TaskType.MONTHLY -> {
                val (startTimestamp, endTimestamp) = getStartAndEndTimestamps(date, taskType)
                getTasksJob =
                    taskUseCases.getMonthlyTasksForMonthUseCase(startTimestamp, endTimestamp)
                        .onEach { tasks ->
                            _state.value = state.value.copy(tasks = tasks)
                        }.launchIn(viewModelScope)
            }

            TaskType.YEARLY -> {
                val (startTimestamp, endTimestamp) = getStartAndEndTimestamps(date, taskType)
                getTasksJob =
                    taskUseCases.getYearlyTasksForYearUseCase(startTimestamp, endTimestamp)
                        .onEach { tasks ->
                            _state.value = state.value.copy(tasks = tasks)
                        }.launchIn(viewModelScope)
            }
        }
    }

    fun getStartAndEndTimestamps(date: String, taskType: TaskType): Pair<Long, Long> {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate = LocalDateTime.parse("${date}T00:00", inputFormatter)

        return when (taskType) {
            TaskType.MONTHLY -> {
                val startOfMonth = parsedDate.withDayOfMonth(1).withHour(0).withMinute(0)
                val endOfMonth = startOfMonth.plusMonths(1)
                Pair(
                    startOfMonth.toEpochSecond(ZoneOffset.UTC),
                    endOfMonth.toEpochSecond(ZoneOffset.UTC)
                )
            }

            TaskType.YEARLY -> {
                val startOfYear = parsedDate.withDayOfYear(1).withHour(0).withMinute(0)
                val endOfYear = startOfYear.plusYears(1)
                Pair(
                    startOfYear.toEpochSecond(ZoneOffset.UTC),
                    endOfYear.toEpochSecond(ZoneOffset.UTC)
                )
            }

            else -> throw IllegalArgumentException("Type must be either 'month' or 'year'")
        }
    }
//
//    fun onScreenDisplayed(newDate: String) {
//        if (date != newDate) {
//            date = newDate
//            getTasksForDate(newDate)
//        }
//    }
}