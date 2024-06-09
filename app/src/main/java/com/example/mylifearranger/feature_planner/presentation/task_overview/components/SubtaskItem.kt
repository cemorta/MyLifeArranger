package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.Subtask

@Composable
fun SubtaskItem(subtask: Subtask, onDelete: () -> Unit, onCheckedChange: (Boolean) -> Unit, onTitleValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .clickable {
                    // navigate to the subtask overview screen
                    subtask.id?.let {  }
                }
        ) {
            Checkbox(
                checked = subtask.isDone,
                onCheckedChange = { isChecked ->
                    onCheckedChange(isChecked)
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                value = subtask.title,
                singleLine = true,
                onValueChange = {
                    onTitleValueChange(it)
                },
                placeholder = { Text(subtask.title, color = Color.Gray) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            IconButton(
                onClick = {
                    onDelete()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete subtask",
                )
            }
        }
    }
}