//package com.example.mylifearranger.feature_planner.presentation.task_details
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.mylifearranger.feature_planner.domain.use_case.task.TaskUseCases
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class TaskDetailsViewModel @Inject constructor(
//    private val taskUseCases: TaskUseCases,
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//    private val _state = mutableStateOf(TaskDetailsState())
//    val state: State<TaskDetailsState> = _state
//
//    init {
//        savedStateHandle.get<Int>("taskId")?.let { taskId ->
//            if (taskId != -1) {
//                viewModelScope.launch {
//                    taskUseCases.getTaskUseCase(taskId).also { task ->
//                        _state.value = state.value.copy(task = task)
//                    }
//                }
//            }
//        }
//    }
//
//    fun onEvent(event: TaskDetailsEvent) {
//        when (event) {
//            is TaskDetailsEvent.DeleteTask -> {
//                viewModelScope.launch {
//                    taskUseCases.deleteTaskUseCase(event.task)
//                }
//            }
//        }
//    }
//
////    private fun getTaskById(taskId: Int) {
////        getTaskByIdJob?.cancel()
////        getTaskByIdJob = taskUseCases.getTaskUseCase(taskId).onEach { task ->
////            _state.value = state.value.copy(task = task)
////        }.launchIn(viewModelScope)
////    }
//}