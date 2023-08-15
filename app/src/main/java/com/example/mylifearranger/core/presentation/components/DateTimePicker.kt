package com.example.mylifearranger.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimePicker(
    initialDateValue: LocalDate? = null,
    initialTimeValue: LocalTime? = null,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Column {
        DatePicker(
            label = "Date",
            value = initialDateValue?.format(dateFormatter) ?: "",
            onValueChange = {
                onDateSelected(LocalDate.parse(it, dateFormatter))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TimePicker(
            label = "Time",
            value = initialTimeValue?.format(timeFormatter) ?: "",
            onValueChange = {
                onTimeSelected(LocalTime.parse(it, timeFormatter))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
