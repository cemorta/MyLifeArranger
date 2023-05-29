package com.example.mylifearranger.feature_planner.presentation.util

sealed class Screen(val route: String) {
    object DayViewScreen : Screen("day_view_screen")
    object AddEditEventScreen : Screen("add_edit_event_screen")
    object EventDetailsScreen : Screen("event_details_screen")
}
