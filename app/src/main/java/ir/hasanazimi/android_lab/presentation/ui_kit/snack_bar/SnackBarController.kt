package ir.hasanazimi.android_lab.presentation.ui_kit.snack_bar

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/***
 * Best practice of this document
 * LINK -> https://afigaliyev.medium.com/snackbar-state-management-best-practices-for-jetpack-compose-1a5963d86d98
 */


interface SnackBarController {
    fun showSnackBar(message: String, isSuccess: Boolean? = null)
    fun dismissAll()
}

class GlobalSnackBarController(
    val snackBarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope
) : SnackBarController {

    private val TAG = this::class.java.simpleName

    init {
        Log.i(TAG, "init: ")
    }

    override fun showSnackBar(message: String, isSuccess: Boolean?) {
        coroutineScope.launch {
            Log.d(TAG, "showSnackBar ==> message : $message  [isSuccess : $isSuccess]")
            snackBarHostState.currentSnackbarData?.dismiss()
            snackBarHostState.showSnackbar(
                message = message,
                actionLabel = isSuccess?.toString(),
                duration = SnackbarDuration.Short ,
            )
        }
    }


    override fun dismissAll() {
        snackBarHostState.currentSnackbarData?.dismiss()
        Log.i(TAG, "dismissAll: ")
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