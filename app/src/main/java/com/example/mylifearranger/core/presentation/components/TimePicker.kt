package com.example.mylifearranger.core.presentation.components

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
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

    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.Top,
                unbounded = true
            )
    ) {
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
                Icon(
                    painterResource(id = R.drawable.baseline_schedule_24),
                    contentDescription = "Select time"
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f)  // Ensure TextField is visually behind the overlay
        )
        Box(
            modifier = Modifier
                .matchParentSize()  // Makes the overlay match the size of the TextField
                .background(Color.Transparent)  // Keeps the overlay invisible
                .clickable { dialog.show() }
                .zIndex(1f)  // Ensures this overlay is clickable and in front
        )
    }
}
