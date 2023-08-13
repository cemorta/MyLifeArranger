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
import toTimestamp
import java.time.LocalDateTime
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

    fun onEvent(event: TaskViewEvent) {
        when (event) {
            is TaskViewEvent.FilterTaskType -> {
                _state.value = state.value.copy(taskType = event.taskType)
                getTasksForDateAndType(event.date, event.taskType)
            }
        }
    }
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
                getTasksJob = taskUseCases.getDailyTasksForDateUseCase(date).onEach { tasks ->
                    _state.value = state.value.copy(tasks = tasks)
                }.launchIn(viewModelScope)
            }

            TaskType.MONTHLY -> {
                val (timestampStartOfTheMonth, timestampEndOfTheMonth) = getStartAndEndTimestamps(date, taskType)
                getTasksJob =
                    taskUseCases.getMonthlyTasksForMonthUseCase(timestampStartOfTheMonth, timestampEndOfTheMonth)
                        .onEach { tasks ->
                            _state.value = state.value.copy(tasks = tasks)
                        }.launchIn(viewModelScope)
            }

            TaskType.YEARLY -> {
                val (timestampStartOfTheYear, timestampEndOfTheYear) = getStartAndEndTimestamps(date, taskType)
                getTasksJob =
                    taskUseCases.getYearlyTasksForYearUseCase(timestampStartOfTheYear, timestampEndOfTheYear)
                        .onEach { tasks ->
                            _state.value = state.value.copy(tasks = tasks)
                        }.launchIn(viewModelScope)
            }

            TaskType.NONE -> {
                getTasksJob = taskUseCases.getNoneTasksUseCase().onEach { tasks ->
                    _state.value = state.value.copy(tasks = tasks)
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getStartAndEndTimestamps(date: String, taskType: TaskType): Pair<Long, Long> {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val parsedDate = LocalDateTime.parse("${date}T00:00", inputFormatter)

        return when (taskType) {
            TaskType.MONTHLY -> {
                val startOfMonth = parsedDate.withDayOfMonth(1).withHour(0).withMinute(0)
                val endOfMonth = startOfMonth.plusMonths(1)
                Pair(
                    startOfMonth.toTimestamp(),
                    endOfMonth.toTimestamp()
                )
            }

            TaskType.YEARLY -> {
                val startOfYear = parsedDate.withDayOfYear(1).withHour(0).withMinute(0)
                val endOfYear = startOfYear.plusYears(1)
                Pair(
                    startOfYear.toTimestamp(),
                    endOfYear.toTimestamp()
                )
            }

            else -> throw IllegalArgumentException("Type must be either 'month' or 'year'")
        }
    }
}