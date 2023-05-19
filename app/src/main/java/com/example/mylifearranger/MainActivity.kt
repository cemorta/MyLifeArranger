package com.example.mylifearranger

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.mylifearranger.ui.theme.MyLifeArrangerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val currentDate = LocalDate.now()
        super.onCreate(savedInstanceState)
        setContent {
            MyLifeArrangerTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
                DayScreen(currentDate)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayScreen(currentDate: LocalDate?) {
    val date = currentDate?.format(DateTimeFormatter.ISO_DATE)
    val appTitle: String = date.toString()
    Scaffold(topBar = { AppBar(appTitle) }, bottomBar = { BottomBar() }) {
        // Create a Surface and add padding around it
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    it.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    it.calculateTopPadding(),
                    it.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    it.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                WeekDaysRow(currentDate)
                Divider()
                TimelineView()
            }
        }
    }
}

@Composable
fun TimelineView() {
    val scrollState = rememberScrollState()

    // Create a list of hours to display in the timeline
    val hours = List(24) { String.format("%02d:00", it) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(60.dp) // Add space between items
        ) {
            for (hour in hours) {
                Text(hour)
            }
        }
        EventRows()
    }
}

@Composable
fun EventRows() {
    Column {
        Spacer(modifier = Modifier.height(returnClockPosition("01:00").dp))
        EventRow("Event 1")
    }
}

fun returnClockPosition(time: String): Float {
    val initialPadding = 11
    val paddingBetweenHours: Float = 81.5F
    val paddingBetweenMinutes: Float = 1.35F

    val hour = time.substring(0, 2).toInt()
    val minutes = time.substring(3, 5).toInt()
    val result: Float =
        hour * paddingBetweenHours + initialPadding + minutes * paddingBetweenMinutes
    return result
}

@Composable
fun EventRow(eventName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(alpha = 0.5f))
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(eventName, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
    }
}

@Composable
fun WeekDaysRow(currentDate: LocalDate?) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val startDate = LocalDate.now().withDayOfMonth(1)
    val endDate = LocalDate.now().withDayOfMonth(startDate.lengthOfMonth())
    val daysOfMonth = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1
    val dates = (1..daysOfMonth).map {
        startDate.plusDays(it.toLong() - 1)
    }
    if (selectedDate == null) selectedDate = currentDate

    LazyRow(
        modifier = Modifier.padding(8.dp), // Add padding around the LazyRow
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add space between items
    ) {
        items(dates) { date ->
            DaySquare(date = date, isSelected = date == selectedDate) {
                selectedDate = date
            }
        }
    }
}

@Composable
fun DaySquare(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val dayOfWeek = date.dayOfWeek.getDisplayName(
        java.time.format.TextStyle.SHORT,
        java.util.Locale.getDefault()
    )
    Box(
        modifier = Modifier
            .clickable(onClick = onClick) // Add a click listener
            .size(50.dp) // Set the size of the square
            .background(
                color = if (isSelected) Color.Blue else Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            ), // Set the background color and shape
        contentAlignment = Alignment.Center // Align the content to the center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(dayOfWeek)
            Text(date.dayOfMonth.toString())
        }
    }
}

@Composable
fun BottomBar(items: List<BottomTabItem> = BottomTabItem.items) {
    var width by remember { mutableStateOf(0) }
    var selectedItem by remember { mutableStateOf(items[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size -> width = size.width }
            .background(color = Color.LightGray),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEach { item ->
                BottomBarItem(
                    barItem = item,
                    width = width / items.size,
                    isSelected = item == selectedItem
                ) {
                    selectedItem = item
                }
            }
        }
    }
}

@Composable
fun BottomBarItem(
    barItem: BottomTabItem,
    width: Int,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onItemClick() }
            .width(with(LocalDensity.current) { width.toDp() })
            .background(
                color = if (isSelected) Color.Blue else Color.LightGray,
            )
    ) {
        Icon(painter = painterResource(id = barItem.icon), contentDescription = null)
        Text(barItem.itemText)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(title = { Text(text = title) }, actions = {
        ActionIconButton(
            icon = R.drawable.baseline_calendar_today_24,
            contentDescription = "Today",
            onClick = { println("hello") })
        ActionIconButton(
            icon = R.drawable.baseline_calendar_month_24,
            contentDescription = "Pick Date",
            onClick = { println("hello") })
        ActionIconButton(
            icon = R.drawable.baseline_settings_24,
            contentDescription = "View Settings",
            onClick = { println("hello") })
    }
    )
}

@Composable
fun ActionIconButton(icon: Int, contentDescription: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(id = icon), contentDescription = contentDescription)
    }
}

@Preview(showBackground = true)
@Composable
fun DayScreenPreview() {
    MyLifeArrangerTheme {
        DayScreen(LocalDate.now())
    }
}