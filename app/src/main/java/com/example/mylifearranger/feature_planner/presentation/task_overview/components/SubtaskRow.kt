package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.feature_planner.domain.model.Subtask

@Composable
fun SubtaskRow(subtask: Subtask, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // navigate to the subtask overview screen
                subtask.id?.let {  }
            }
            .height(50.dp)
            .padding(start = 5.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = subtask.isDone,
            onCheckedChange = { isChecked ->
                onCheckedChange(isChecked)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.padding(start = 5.dp)) {
            Text(text = subtask.title)
        }
    }
}