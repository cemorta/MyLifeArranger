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
import toTimestamp
import java.time.LocalDateTime
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

//    private val _taskStartDateTime = mutableStateOf<LocalDateTime>(LocalDateTime.now())
//    val taskStartDateTime: State<LocalDateTime> = _taskStartDateTime
//
//    // Create an taskEndDateTime with a default time value of taskStartTime + 1 hour
//    private val _taskEndDateTime = mutableStateOf<LocalDateTime>(LocalDateTime.now().plusHours(1))
//    val taskEndDateTime: State<LocalDateTime> = _taskEndDateTime

    private val _eventFlow = MutableSharedFlow<UiEvent>()
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

    fun onEvent(task: AddEditTaskEvent) {
        when (task) {
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = task.value
                )
            }

            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !task.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }

            is AddEditTaskEvent.SaveEvent -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTaskUseCase(
                            Task(
                                title = taskTitle.value.text,
                                duration = 1800,
                                taskType = TaskType.DAILY,
                                plannedTimestamp = LocalDateTime.now().toTimestamp(),
                                setPlannedTime = false,
                                isDone = false,
                                dueTimestamp = null,
                                assignedGoalId = null,
                                assignedEventId = null,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveEvent)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveEvent : UiEvent()
    }
}