package com.example.mylifearranger.feature_planner.presentation.task_overview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.TaskTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import toLocalDateTime
import toTimestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TaskOverviewViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentTaskId: Int? = null

    private val _taskTitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter title"
        )
    )
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskDurationHour = mutableStateOf(
        TaskTextFieldState(
            hint = "hour"
        )
    )
    val taskDurationHour: State<TaskTextFieldState> = _taskDurationHour

    private val _taskDurationMinute = mutableStateOf(
        TaskTextFieldState(
            hint = "minute"
        )
    )
    val taskDurationMinute: State<TaskTextFieldState> = _taskDurationMinute

    private val _taskType = mutableStateOf<TaskType?>(null)
    val taskType: State<TaskType?> = _taskType

    private val _taskPlannedLocalDateTime =
        mutableStateOf<LocalDateTime?>(null)
    val taskPlannedLocalDateTime: State<LocalDateTime?> = _taskPlannedLocalDateTime

    private val _isPlannedTimeSet = mutableStateOf<Boolean?>(null)
    val isPlannedTimeSet: State<Boolean?> = _isPlannedTimeSet

    private val _isDone = mutableStateOf<Boolean?>(null)
    val isDone: State<Boolean?> = _isDone

    private val _dueLocalDateTime = mutableStateOf<LocalDateTime?>(null)
    val dueLocalDateTime: State<LocalDateTime?> = _dueLocalDateTime

    private val _isDueTimeSet = mutableStateOf<Boolean?>(null)
    val isDueTimeSet: State<Boolean?> = _isDueTimeSet

    private val _assignedGoalId = mutableStateOf<Int?>(null)
    val assignedGoalId: State<Int?> = _assignedGoalId

    private val _assignedEventId = mutableStateOf<Int?>(null)
    val assignedEventId: State<Int?> = _assignedEventId

    private var getTasksJob: Job? = null

    init {
        // Get task id from savedStateHandle and get task from database
        savedStateHandle.get<Int>("taskId")?.let { taskId ->

            // If task id is not -1, get task from database
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTaskUseCase(taskId)?.also { task ->

                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        if (task.duration != null) {
                            _taskDurationHour.value = taskDurationHour.value.copy(
                                text = (task.duration!! / 60).toString(),
                                isHintVisible = false
                            )
                            _taskDurationMinute.value = taskDurationMinute.value.copy(
                                text = (task.duration!! % 60).toString(),
                                isHintVisible = false
                            )
                        }
                        _taskType.value = task.taskType
                        if (task.plannedTimestamp != null) {
                            _isPlannedTimeSet.value = task.isPlannedTimeSet
                            _taskPlannedLocalDateTime.value =
                                task.plannedTimestamp.toLocalDateTime()
                        }
                        if (task.dueTimestamp != null) {
                            _isDueTimeSet.value = task.isDueTimeSet
                            _dueLocalDateTime.value = task.dueTimestamp.toLocalDateTime()
                        }
                    }
                }
            }
        }
    }

    fun onAction(action: TaskOverviewAction) {
        when (action) {
            TaskOverviewAction.MarkAsCompleted -> TODO()
            TaskOverviewAction.MarkAsIncomplete -> TODO()
            TaskOverviewAction.DeleteTask -> TODO()
        }
    }
}