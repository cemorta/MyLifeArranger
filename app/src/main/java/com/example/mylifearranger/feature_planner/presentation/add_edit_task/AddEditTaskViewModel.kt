package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toLocalDateTime
import toTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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

    private val _taskType = mutableStateOf<TaskType>(TaskType.DAILY)
    val taskType: State<TaskType> = _taskType

    private val _taskPlannedLocalDateTime =
        mutableStateOf(LocalDateTime.now().plusHours(1))
    val taskPlannedLocalDateTime: State<LocalDateTime> = _taskPlannedLocalDateTime

    private val _isTimeSet = mutableStateOf(false)
    val isTimeSet: State<Boolean> = _isTimeSet

    private val _isDone = mutableStateOf(false)
    val isDone: State<Boolean> = _isDone

    private val _dueLocalDateTime = mutableStateOf<LocalDateTime?>(null)
    val dueLocalDateTime: State<LocalDateTime?> = _dueLocalDateTime

    private val _assignedGoalId = mutableStateOf<Int?>(null)
    val assignedGoalId: State<Int?> = _assignedGoalId

    private val _assignedEventId = mutableStateOf<Int?>(null)
    val assignedEventId: State<Int?> = _assignedEventId

    private val _eventFlow = MutableSharedFlow<UiAction>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTaskUseCase(taskId)?.also { task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
//                        _taskColor.value = task.color
//                        _taskStartDateTime.value = task.startTimestamp.toLocalDateTime()
//                        _taskEndDateTime.value = task.endTimestamp.toLocalDateTime()
                    }
                }
            }
        }
    }

    fun onEvent(task: AddEditTaskAction) {
        when (task) {
            is AddEditTaskAction.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = task.value
                )
            }

            is AddEditTaskAction.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !task.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }

            is AddEditTaskAction.EnteredDurationHour -> {

//                if (taskDurationHour.value.text.toIntOrNull() != null) {
//
//                        _taskDurationHour.value = taskDurationHour.value.copy(
//                            text = task.value
//                        )
//                }
                // Copy the value of the text field
                _taskDurationHour.value = taskDurationHour.value.copy(
                    text = task.value
                )
//                _taskDuration.value.let {
//                    if (it != null) {
//                        _taskDuration.value = it + task.value.toLong() * 3600
//                    } else {
//                        _taskDuration.value = task.value.toLong() * 3600
//                    }
//                }
            }

            is AddEditTaskAction.EnteredDurationMinute -> {

//                if (taskDurationMinute.value.text.toIntOrNull() != null) {
//
//                    _taskDurationMinute.value = taskDurationMinute.value.copy(
//                        text = task.value
//                    )
//                }
                // Copy the value of the text field
                _taskDurationMinute.value = taskDurationMinute.value.copy(
                    text = task.value
                )
            }

            is AddEditTaskAction.ChangeDurationHourFocus -> {

//                if (taskDurationHour.value.text.toIntOrNull() != null) {
//
//                    _taskDurationHour.value = taskDurationHour.value.copy(
//                        text = task.value
//                    )
////
////                        _taskDurationHour.value = taskDurationHour.value.copy(
////                            text = task.value
////                        )
//                }
                _taskDurationHour.value = taskDurationHour.value.copy(
                    isHintVisible = !task.focusState.isFocused &&
                            taskDurationHour.value.text.isBlank()
                )
            }

            is AddEditTaskAction.ChangeDurationMinuteFocus -> {
                // If the minute is greater than 60, and the hour and minute are integers, add the excess minutes to the hour and set the minute modulo 60 when unfocused minute text field
                if (_taskDurationMinute.value.text.toIntOrNull() != null && _taskDurationMinute.value.text.toInt() >= 60 && _taskDurationHour.value.text.toIntOrNull() != null && !task.focusState.isFocused) {

                    _taskDurationHour.value = taskDurationHour.value.copy(
                        text = (taskDurationHour.value.text.toInt() + taskDurationMinute.value.text.toInt() / 60).toString()
                    )
                    _taskDurationMinute.value = taskDurationMinute.value.copy(
                        text = (taskDurationMinute.value.text.toInt() % 60).toString()
                    )
                }

                _taskDurationMinute.value = taskDurationMinute.value.copy(
                    isHintVisible = !task.focusState.isFocused &&
                            taskDurationMinute.value.text.isBlank()
                )
            }
            is AddEditTaskAction.EnteredTaskType -> {
                _taskType.value = task.value
            }
            is AddEditTaskAction.EnteredPlannedDate -> {
                val newPlannedLocalDateTime = LocalDateTime.of(task.value, _taskPlannedLocalDateTime.value.toLocalTime())
                _taskPlannedLocalDateTime.value = newPlannedLocalDateTime
            }
            is AddEditTaskAction.EnteredPlannedTime -> {
                val newPlannedLocalDateTime = LocalDateTime.of(_taskPlannedLocalDateTime.value.toLocalDate(), task.value)
                _taskPlannedLocalDateTime.value = newPlannedLocalDateTime
            }
            is AddEditTaskAction.EnteredDueDate -> {
                val newDueLocalDateTime = LocalDateTime.of(task.value, _dueLocalDateTime.value?.toLocalTime() ?: LocalTime.MIDNIGHT)
                _dueLocalDateTime.value = newDueLocalDateTime
            }
            is AddEditTaskAction.EnteredDueTime -> {
                val newDueLocalDateTime = LocalDateTime.of(_dueLocalDateTime.value?.toLocalDate() ?: LocalDate.now(), task.value)
                _dueLocalDateTime.value = newDueLocalDateTime
            }

            is AddEditTaskAction.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTaskUseCase(
                            Task(
                                title = taskTitle.value.text,
                                duration = taskDurationHour.value.text.toInt() * 3600L + taskDurationMinute.value.text.toInt() * 60L,
                                taskType = taskType.value,
                                plannedTimestamp = taskPlannedLocalDateTime.value.toTimestamp(),
                                isPlannedTimeSet = true,
                                isDone = false,
                                dueTimestamp = dueLocalDateTime.value?.toTimestamp(),
                                isDueTimeSet = true,
                                assignedGoalId = null,
                                assignedEventId = null,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiAction.SaveTask)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiAction.ShowSnackbar(
                                message = e.message ?: "Couldn't save task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiAction {
        data class ShowSnackbar(val message: String) : UiAction()
        object SaveTask : UiAction()
    }
}