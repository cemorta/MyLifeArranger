package com.example.mylifearranger.core.presentation.components

import android.app.TimePickerDialog
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.mylifearranger.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "HH:mm",
    is24HourView: Boolean = true,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val time = if (value.isNotBlank()) LocalTime.parse(value, formatter) else LocalTime.now()
    val dialog = TimePickerDialog(
        LocalContext.current,
        { _, hour, minute -> onValueChange(LocalTime.of(hour, minute).toString()) },
        time.hour,
        time.minute,
        is24HourView,
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        readOnly = true,
        enabled = true,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { dialog.show() }) {
                Icon(painterResource(id = R.drawable.baseline_schedule_24), contentDescription = "Select time")
            }
        }
    )
}
