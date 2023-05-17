package com.example.mylifearranger

data class BottomTabItem(val itemText: String, val icon: Int) {
    companion object {
        val items = listOf(
            BottomTabItem("Day", R.drawable.baseline_view_day_24),
            BottomTabItem("Plans", R.drawable.baseline_menu_book_24),
            BottomTabItem("Goals", R.drawable.baseline_task_alt_24),
            BottomTabItem("Calendar", R.drawable.baseline_calendar_view_month_24),
            BottomTabItem("Settings", R.drawable.baseline_settings_24)
        )
    }
}
