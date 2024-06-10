package com.example.mylifearranger.feature_planner.presentation.task_view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun IconButtonBar(
    onAddClick: () -> Unit, onClickCompleted: () -> Unit, filter: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        // Icon for Menu
        IconButton(
            onClick = { /* handle click */ },
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
            )
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.Black
            )
        }

        // Icon for Email
        IconButton(
            onClick = { /* handle click */ },

            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
            )
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email",
                tint = Color.Black
            )
        }

        // Central Icon (Add) - Circular Button
        IconButton(
            onClick = {
                onAddClick()
            },
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.small
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.Black
            )
        }

        // Icon for Check
        IconButton(
            onClick = {
                      onClickCompleted()
            },
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
            )
                .background(
                    color = if (filter == 1) Color.Green else Color.White
                )
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
                tint = Color.Black
            )
        }

        // Icon for Navigate Next
        IconButton(
            onClick = { /* handle click */ },
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.Black,
            )
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Tag",
                tint = Color.Black
            )
        }
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun IconButtonBarPreview() {
//    Scaffold {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    it.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
//                    it.calculateTopPadding(),
//                    it.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
//                    it.calculateBottomPadding()
//                ),
//            color = MaterialTheme.colorScheme.background
//        ) {
//
//            IconButtonBar()
//        }
//    }
//}
