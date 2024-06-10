package com.example.mylifearranger.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class BottomBarItem(val itemText: String, val icon: Int, val route: String? = null) {
    companion object {
        val items = listOf(
            BottomBarItem(
                "Day",
                R.drawable.baseline_view_day_24,
                Screen.DayViewScreen.route + "?date=${
                    LocalDate.parse(
                        LocalDate.now().toString(),
                        DateTimeFormatter.ISO_DATE
                    )
                }"
            ),
            BottomBarItem(
                "Plans",
                R.drawable.baseline_menu_book_24,
                Screen.PlanViewScreen.route
            ),
            BottomBarItem(
                "Tasks",
                R.drawable.baseline_task_alt_24,
                Screen.TaskViewScreen.route + "?taskType=DAILY&date=${
                    LocalDate.parse(
                        LocalDate.now().toString(),
                        DateTimeFormatter.ISO_DATE
                    )
                }"
            ),
//            BottomBarItem(
//                "Calendar",
//                R.drawable.baseline_calendar_view_month_24
//            ),
//            BottomBarItem(
//                "Settings",
//                R.drawable.baseline_settings_24
//            )
        )
    }
}

@Composable
fun BottomBarItemComposable(
    barItem: BottomBarItem,
    width: Int,
    isSelected: Boolean,
    navController: NavController,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
                barItem.route?.let {
                    navController.navigate(it) {
                        // Clear the back stack until the start destination (not included) and launch the route of the selected item
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            }
            .width(with(LocalDensity.current) { width.toDp() })
            .background(
                color = if (isSelected) Color.DarkGray else Color.LightGray,
            )
    ) {
        Icon(painter = painterResource(id = barItem.icon), contentDescription = null)
        Text(
            barItem.itemText,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}
