package com.example.mylifearranger.feature_planner.presentation.add_edit_event.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.core.presentation.components.DateTimePicker
import com.example.mylifearranger.core.presentation.components.TransparentHintTextField
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.EventTextFieldState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun FormContainer(
    titleState: EventTextFieldState,
    startDateState: LocalDateTime,
    endDateState: LocalDateTime,
    onTitleChange: (String) -> Unit,
    onTitleFocusChange: (FocusState) -> Unit,
    onStartDateSelected: (LocalDate) -> Unit,
    onStartTimeSelected: (LocalTime) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
    onEndTimeSelected: (LocalTime) -> Unit,
) {
    TransparentHintTextField(
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
            onTitleChange(it)
        },
        onFocusChange = {
            onTitleFocusChange(it)
        },
        isHintVisible = titleState.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Start date",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth()
    )
    DateTimePicker(
        initialDateValue = startDateState.toLocalDate(),
        initialTimeValue = startDateState.toLocalTime(),
        onDateSelected = {
            onStartDateSelected(it)
        },
        onTimeSelected = {
            onStartTimeSelected(it)
        },
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "End date",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth()
    )
    DateTimePicker(
        initialDateValue = endDateState.toLocalDate(),
        initialTimeValue = endDateState.toLocalTime(),
        onDateSelected = {
            onEndDateSelected(it)
        },
        onTimeSelected = {
            onEndTimeSelected(it)
        },
    )
}