package com.example.mylifearranger.feature_planner.presentation.add_edit_event.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.core.presentation.components.DateTimePicker
import com.example.mylifearranger.core.presentation.components.TransparentHintTextField
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.AddEditEventAction
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.EventTextFieldState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun FormContainer(
    titleState: EventTextFieldState,
    startDateState: LocalDateTime,
    endDateState: LocalDateTime,
    onEvent: (AddEditEventAction) -> Unit,
) {
    TransparentHintTextField(
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
            onEvent(AddEditEventAction.EnteredTitle(it))
        },
        onFocusChange = {
            onEvent(AddEditEventAction.ChangeTitleFocus(it))
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
        Text(
            text = "Start date",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
        Column(modifier = Modifier.padding(20.dp, 10.dp, 10.dp, 10.dp)) {
            DateTimePicker(
                initialDateValue = startDateState.toLocalDate(),
                initialTimeValue = startDateState.toLocalTime(),
                onDateSelected = {
                    onEvent(AddEditEventAction.EnteredStartDate(it))
                },
                onTimeSelected = {
                    onEvent(AddEditEventAction.EnteredStartTime(it))
                },
            )
        }
    }
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "End date",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )
        Column(modifier = Modifier.padding(20.dp, 10.dp, 10.dp, 10.dp)) {
            DateTimePicker(
                initialDateValue = endDateState.toLocalDate(),
                initialTimeValue = endDateState.toLocalTime(),
                onDateSelected = {
                    onEvent(AddEditEventAction.EnteredEndDate(it))
                },
                onTimeSelected = {
                    onEvent(AddEditEventAction.EnteredEndTime(it))
                },
            )
        }
    }
}