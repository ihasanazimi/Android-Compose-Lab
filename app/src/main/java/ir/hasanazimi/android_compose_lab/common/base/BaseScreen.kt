package ir.hasanazimi.android_compose_lab.common.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ir.hasanazimi.android_compose_lab.presentation.theme.AppTheme
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.bottom_navigation_bar.BottomNavigationBar
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.headers.GeneralHeader
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.snack_bar.CustomSnackBar
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.snack_bar.GlobalSnackBarController
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.snack_bar.LocalSnackBarController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    val snackBarController = LocalSnackBarController.current as? GlobalSnackBarController

    LaunchedEffect(Unit) {
        snackBarController?.dismissAll()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().then(modifier),
        topBar = {
            topBar?.let { tb ->
                CenterAlignedTopAppBar(
                    title = tb,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        },
        bottomBar = { bottomBar?.invoke() },
        snackbarHost = {
            snackBarController?.let { sbc ->
                SnackbarHost(
                    hostState = sbc.snackBarHostState,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) { data ->
                    val isSuccess = data.visuals.actionLabel?.toBooleanStrictOrNull()
                    CustomSnackBar(
                        textMessage = data.visuals.message,
                        isSuccessfully = isSuccess
                    )
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}




@Preview(showBackground = true)
@Composable
fun BaseScreenContentPreview() {

    /** sample */
    AppTheme {
        BaseScreen(
            topBar = {
                GeneralHeader(
                    title = "title"
                ) { }
            },
            bottomBar = {
                BottomNavigationBar(
                    hostNavController = rememberNavController(),
                    currentDestination = ""
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "محتوای صفحه",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }

}