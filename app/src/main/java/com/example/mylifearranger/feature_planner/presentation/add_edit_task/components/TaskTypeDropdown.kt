package com.example.mylifearranger.feature_planner.presentation.add_edit_task.components

import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.mylifearranger.core.presentation.util.toResId
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Composable
fun TaskTypeDropdown(selectedType: TaskType, onTypeSelected: (TaskType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Button(onClick = { expanded = true }) {
        Text(text = context.getString(selectedType.toResId()))
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        TaskType.values().forEach { type ->
            DropdownMenuItem(onClick = {
                onTypeSelected(type)
                expanded = false
            }, text = {
                Text(text = context.getString(type.toResId()))
            })
        }
    }
}
