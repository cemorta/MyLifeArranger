package com.example.mylifearranger.feature_planner.presentation.task_overview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Subtask
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

    private val _subtasks = mutableStateOf<List<Subtask>>(ArrayList())
    val subtasks: State<List<Subtask>> = _subtasks

    private var getTasksJob: Job? = null

    init {
        // Get task id from savedStateHandle and get task from database
        savedStateHandle.get<Int>("taskId")?.let { taskId ->

            // If task id is not -1, get task from database
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTaskUseCase(taskId)?.also { taskWithSubtask ->

                        currentTaskId = taskWithSubtask.task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = taskWithSubtask.task.title,
                            isHintVisible = false
                        )
                        if (taskWithSubtask.task.duration != null) {
                            _taskDurationHour.value = taskDurationHour.value.copy(
                                text = (taskWithSubtask.task.duration / 3600).toString(),
                                isHintVisible = false
                            )
                            _taskDurationMinute.value = taskDurationMinute.value.copy(
                                text = ((taskWithSubtask.task.duration % 3600) / 60).toString(),
                                isHintVisible = false
                            )
                        }
                        _taskType.value = taskWithSubtask.task.taskType
                        if (taskWithSubtask.task.plannedTimestamp != null) {
                            _isPlannedTimeSet.value = taskWithSubtask.task.isPlannedTimeSet
                            _taskPlannedLocalDateTime.value =
                                taskWithSubtask.task.plannedTimestamp.toLocalDateTime()
                        }
                        if (taskWithSubtask.task.dueTimestamp != null) {
                            _isDueTimeSet.value = taskWithSubtask.task.isDueTimeSet
                            _dueLocalDateTime.value = taskWithSubtask.task.dueTimestamp.toLocalDateTime()
                        }
                        if (taskWithSubtask.subtasks.isNotEmpty()) {
                            _subtasks.value = taskWithSubtask.subtasks
                        }
                    }
                }
            }
        }
    }

    fun onAction(action: TaskOverviewAction) {
        when (action) {
            is TaskOverviewAction.AddNewSubtask -> {
                _subtasks.value = subtasks.value.plus(
                    Subtask(
                        title = "",
                        isDone = false,
                        id = action.subtaskId,
                        assignedTaskId = null,
                        description = null,
                        assignedEventId = null,
                    )
                )
            }
            is TaskOverviewAction.UpdateSubtaskTitle -> {
                _subtasks.value = subtasks.value.map {
                    if (it.id == action.subtaskId) {
                        it.copy(title = action.title)
                    } else {
                        it
                    }
                }
            }
            is TaskOverviewAction.UpdateSubtaskStatus -> {
                _subtasks.value = subtasks.value.map {
                    if (it.id == action.subtaskId) {
                        it.copy(isDone = action.isDone)
                    } else {
                        it
                    }
                }
            }
            is TaskOverviewAction.RemoveSubtask -> {
                _subtasks.value = subtasks.value.filter { it.id != action.subtaskId }
            }
        }
    }
}