package com.example.mylifearranger.core.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.mylifearranger.R
import org.intellij.lang.annotations.JdkConstants.BoxLayoutAxis
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy-MM-dd",
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )

    Box(
        modifier = Modifier
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
            enabled = true,
            readOnly = true,
            singleLine = true,
            trailingIcon = {
                Icon(
                    painterResource(id = R.drawable.baseline_calendar_today_24),
                    contentDescription = "Select date"
                )
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
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
