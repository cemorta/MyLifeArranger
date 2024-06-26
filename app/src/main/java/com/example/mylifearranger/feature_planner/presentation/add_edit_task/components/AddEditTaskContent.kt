package com.example.mylifearranger.feature_planner.presentation.add_edit_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.core.presentation.components.DateTimePicker
import com.example.mylifearranger.core.presentation.components.TransparentHintNumberField
import com.example.mylifearranger.core.presentation.components.TransparentHintTextField
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.AddEditTaskAction
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.TaskTextFieldState
import java.time.LocalDateTime

@Composable
fun AddEditTaskContent(
    titleState: TaskTextFieldState,
    durationHourState: TaskTextFieldState,
    durationMinuteState: TaskTextFieldState,
    taskTypeState: TaskType,
    plannedLocalDateTimeState: LocalDateTime,
    isTimeSetState: Boolean,
    dueLocalDateTimeState: LocalDateTime?,
    onEvent: (AddEditTaskAction) -> Unit
) {

    // TitleSection
    TransparentHintTextField(
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
            onEvent(AddEditTaskAction.EnteredTitle(it))
        },
        onFocusChange = {
            onEvent(AddEditTaskAction.ChangeTitleFocus(it))
        },
        isHintVisible = titleState.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Duration", modifier = Modifier.padding(start = 10.dp, top = 5.dp))

        Column {
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(20.dp)) {

                // DurationSection
                TransparentHintNumberField(
                    modifier = Modifier.width(100.dp),
                    text = durationHourState.text,
                    hint = durationHourState.hint,
                    onValueChange = {
                        onEvent(AddEditTaskAction.EnteredDurationHour(it))
                    },
                    onFocusChange = {
                        onEvent(AddEditTaskAction.ChangeDurationHourFocus(it))
                    },
                    isHintVisible = durationHourState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleMedium,
                )
                TransparentHintNumberField(
                    modifier = Modifier.width(100.dp),
                    text = durationMinuteState.text,
                    hint = durationMinuteState.hint,
                    onValueChange = {
                        onEvent(AddEditTaskAction.EnteredDurationMinute(it))
                    },
                    onFocusChange = {
                        onEvent(AddEditTaskAction.ChangeDurationMinuteFocus(it))
                    },
                    isHintVisible = durationMinuteState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

//    // TaskTypeSection
//    TaskTypeDropdown(selectedType = taskTypeState, onTypeSelected = {
//        onEvent(AddEditTaskAction.EnteredTaskType(it))
//    })
//    Spacer(modifier = Modifier.height(16.dp))

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Planned Date & Time", modifier = Modifier.padding(start = 10.dp, top = 5.dp))

        Column(modifier = Modifier.padding(20.dp)) {
            // PlannedTimestampSection
            DateTimePicker(
                onDateSelected = {
                    onEvent(
                        AddEditTaskAction.EnteredPlannedDate(it)
                    )
                },
                onTimeSelected = {
                    onEvent(
                        AddEditTaskAction.EnteredPlannedTime(it)
                    )
                },
                initialDateValue = plannedLocalDateTimeState.toLocalDate(),
                initialTimeValue = plannedLocalDateTimeState.toLocalTime()
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Due Date & Time", modifier = Modifier.padding(start = 10.dp, top = 5.dp))
        Column(modifier = Modifier.padding(20.dp)) {
            // DueTimestampSection
            DateTimePicker(onDateSelected = {
                onEvent(
                    AddEditTaskAction.EnteredDueDate(it)
                )
            }, onTimeSelected = {
                onEvent(
                    AddEditTaskAction.EnteredDueTime(it)
                )
            },
                initialDateValue = dueLocalDateTimeState?.toLocalDate(),
                initialTimeValue = dueLocalDateTimeState?.toLocalTime()
            )
        }
    }
}