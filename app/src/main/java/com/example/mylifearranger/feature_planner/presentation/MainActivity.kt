package com.example.mylifearranger.feature_planner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mylifearranger.ui.theme.MyLifeArrangerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLifeArrangerTheme {

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DayScreenPreview() {
//    MyLifeArrangerTheme {
//        DayScreen(LocalDate.now())
//    }
//}