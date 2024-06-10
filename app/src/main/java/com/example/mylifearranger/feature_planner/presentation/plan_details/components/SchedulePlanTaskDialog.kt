package com.example.mylifearranger.feature_planner.presentation.plan_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.example.mylifearranger.core.presentation.components.TimePicker
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun SchedulePlanTaskDialog(onConfirm: (initialTime: LocalTime, finalTime: LocalTime) -> Unit, onDismiss: () -> Unit) {
    var initialTimeValue by remember { mutableStateOf<LocalTime?>(null) }
    var finalTimeValue by remember { mutableStateOf<LocalTime?>(null) }
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Schedule Task") },
        text = {
            Column {
                TimePicker(
                    label = "Start Time",
                    value = initialTimeValue?.format(timeFormatter) ?: "",
                    onValueChange = {
                        initialTimeValue = LocalTime.parse(it, timeFormatter)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TimePicker(
                    label = "End Time",
                    value = finalTimeValue?.format(timeFormatter) ?: "",
                    onValueChange = {
                        finalTimeValue = LocalTime.parse(it, timeFormatter)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if(initialTimeValue != null && finalTimeValue != null && initialTimeValue!!.isBefore(finalTimeValue)) {
                        onConfirm(initialTimeValue!!, finalTimeValue!!)
                    }
                    else {
                        onDismiss()
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
