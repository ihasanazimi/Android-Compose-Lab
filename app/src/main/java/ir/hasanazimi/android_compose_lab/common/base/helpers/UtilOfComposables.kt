package ir.hasanazimi.zaghche.common.base

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.Flow



@Composable
fun <T> ObserveSideEffects(flow: Flow<T>, onEffect: (T) -> Unit) {
    LaunchedEffect(flow) {
        flow.collect { onEffect(it) }
    }
}





fun Modifier.safeClick(
    debounceTime: Long = 500L,
    onClick: () -> Unit
): Modifier = composed {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    this.clickable {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            onClick()
            lastClickTime = currentTime
        }
    }
}






/**
Sometimes you need to refresh some data when the screen OnResumes (for example, returning from another page or reopening the app).
Writing a LifecycleObserver inside each screen makes the code cluttered. This Helper is great.
Usage :
OnLifecycleEvent { event ->
    if (event == Lifecycle.Event.ON_RESUME) {
        viewModel.onEvent(MyScreenUiEvent.RefreshData)
    }
}
*/
@Composable
fun OnLifecycleEvent(onEvent: (Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            eventHandler.value(event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}





fun Modifier.bounceClick(
    dampingRatio: Float = Spring.DampingRatioLowBouncy,
    stiffness: Float = Spring.StiffnessVeryLow,
    scaleDown: Float = 0.1f,
    onClick: () -> Unit
): Modifier = composed {

    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDown else 1f,
        animationSpec = spring(
            dampingRatio = dampingRatio,
            stiffness = stiffness
        ),
        label = "bounceClick"
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    onClick()
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                }
            )
        }
}








