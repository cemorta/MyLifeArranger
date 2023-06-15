import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.plan_view.components.PlanList
import com.example.mylifearranger.core.presentation.components.ActionIconButton
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.plan_view.PlanViewViewModel
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanViewScreen(
    navController: NavController,
    viewModel: PlanViewViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Go to add plan screen
                    navController.navigate(Screen.AddEditPlanScreen.route)
                },
                Modifier.background(color = MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add plan"
                )
            }
//    }, topBar = {
//        AppBar(
//            appTitle, taskViewActionButtons(state.taskType,
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.YEARLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.MONTHLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.DAILY, state.date!!)
//                    )
//                }
//            )
//        )
        },
        topBar = {
            AppBar(
                "Plan",
                listOf(
                    ActionIconButton(
                        icon = R.drawable.baseline_filter_list_24,
                        "Filter",
                        onClick = {
                            // Go to filter screen
                        }
                    ),
                    ActionIconButton(
                        icon = R.drawable.baseline_search_24,
                        "Search",
                        onClick = {
                            // Go to search screen
                        }
                    )
                )
            )
        },
        bottomBar = { BottomBar(navController, BottomBarItem.items[1]) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateTopPadding(),
                    paddingValues.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {

//            // Example plans for preview
//            val plans = listOf(
//                Plan(
//                    "Plan 1",
//                    PlanType.TOTAL,
//                    20,
//                    0,
//                    "km",
//                    null,
//                    null,
//                    6,
//                    LocalDateTime.now().toTimestamp(),
//                    LocalDateTime.now().plusHours(1).toTimestamp(),
//                    false,
//                    1,
//                ),
//                Plan(
//                    "Plan 2",
//                    PlanType.RANGE,
//                    null,
//                    0,
//                    "pages",
//                    40,
//                    50,
//                    7,
//                    LocalDateTime.now().toTimestamp(),
//                    LocalDateTime.now().plusHours(1).toTimestamp(),
//                    false,
//                    2,
//                ),
//            )

            // Plan view content
            PlanList(state.plans)


        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlanViewScreenPreview() {
//}