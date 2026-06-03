package ir.hasanazimi.devlab.presentation.ui_kit.bottom_navigation_bar

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.hasanazimi.devlab.R
import ir.hasanazimi.devlab.presentation.theme.AppTheme
import ir.hasanazimi.devlab.presentation.ui_kit.snack_bar.LocalSnackBarController


data class NavigationItemEntity(
    val title: String,
    val drawableResId: Pair<Int, Int>,
    val destinationRouteId: String
)


val navigationItemEntities = listOf(
    NavigationItemEntity(
        title = "خانه",
        drawableResId = Pair(
            R.drawable.baseline_done_24,
            R.drawable.baseline_done_24
        ),
        destinationRouteId = ""
    ),
    NavigationItemEntity(
        title = "بازار سرمایه",
        drawableResId = Pair(
            R.drawable.baseline_done_24,
            R.drawable.baseline_done_24
        ),
        destinationRouteId = ""
    ),
    NavigationItemEntity(
        title = "بیشتر",
        drawableResId = Pair(
            R.drawable.baseline_done_24,
            R.drawable.baseline_done_24
        ),
        destinationRouteId = ""
    )
)


@Composable
fun BottomNavigationBar(
    hostNavController: NavHostController,
    currentDestination: String
) {
    val TAG = "BottomNavigationBar"

    val snackBarController = LocalSnackBarController.current

    AppTheme {
        NavigationBar(containerColor = Color.White) {
            navigationItemEntities.forEach { item ->
                NavigationBarItem(
                    modifier = Modifier.fillMaxWidth().background(Color.White).navigationBarsPadding()
                    ,
                    selected = (currentDestination == item.destinationRouteId).also {
                        Log.i(TAG, "BottomNavigationBar: currentDestination selected [$currentDestination] is $it")
                    },
                    onClick = {
                        if (currentDestination != item.destinationRouteId) {
                            hostNavController.navigate(item.destinationRouteId)
                        }
                        snackBarController.dismissAll()
                    },
                    icon = {
                        Image(
                            modifier = Modifier
                                .padding(horizontal = 4.dp , vertical = 6.dp)
                                .size(if (currentDestination == item.destinationRouteId) 24.dp else 22.dp).animateContentSize(),
                            contentDescription = item.title,
                            painter = painterResource(
                                id = if (currentDestination == item.destinationRouteId) {
                                    item.drawableResId.second
                                } else {
                                    item.drawableResId.first
                                }
                            ),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                    },
                    label = {
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            text = item.title,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    AppTheme {
        BottomNavigationBar(
            hostNavController = rememberNavController(),
            currentDestination = ""
        )
    }
}