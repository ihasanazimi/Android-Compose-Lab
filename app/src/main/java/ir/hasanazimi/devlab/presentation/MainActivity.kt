package ir.hasanazimi.devlab.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.hasanazimi.devlab.presentation.theme.AppTheme
import ir.hasanazimi.devlab.presentation.ui_kit.snack_bar.GlobalSnackBarController
import ir.hasanazimi.devlab.presentation.ui_kit.snack_bar.SnackBarController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")


        enableEdgeToEdge()

        setContent {
            val mainNavController = rememberNavController()
            val snackBarHostState = remember { SnackbarHostState() }
            val snackBarCoroutineScope = rememberCoroutineScope()
            val controller = GlobalSnackBarController(
                snackBarHostState = snackBarHostState,
                coroutineScope = snackBarCoroutineScope
            )
            CompositionLocalProvider(LocalSnackBarController provides controller) {
                SetupApp(navHostController = mainNavController)
            }
        }

    }


    @Composable
    fun SetupApp(navHostController: NavHostController) {
        AppTheme {
            Surface {
                /*AppNavigator(
                    navController = navHostController,
                )*/
            }
        }
    }


    val LocalSnackBarController = staticCompositionLocalOf<SnackBarController> {

        object : SnackBarController {
            override fun showSnackBar(message: String, isSuccess: Boolean?) {
                Log.d("PreviewSnackBar", message)
            }

            override fun dismissAll() {
                // nothing
            }
        }


    }

}