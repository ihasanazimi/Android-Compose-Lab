package ir.hasanazimi.android_compose_lab.presentation.ui_kit.buttons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.android_compose_lab.R
import ir.hasanazimi.android_compose_lab.presentation.theme.AppTheme
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.custom_circle_progressbar.CustomCircleProgressBar
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.UiText
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.asString
import kotlinx.coroutines.delay

open class DefaultState
sealed class ButtonState(open val uiText: UiText) : DefaultState() {
    data class Enable(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) :
        ButtonState(uiText)
    data class Disable(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) :
        ButtonState(uiText)
}
sealed class LoadingButtonState(open val uiText: UiText) : DefaultState() {
    object LoadingButton : LoadingButtonState(UiText.DynamicString(""))
    data class Idle(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) : LoadingButtonState(uiText)
    data class Disable(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) : LoadingButtonState(uiText)
}
sealed class PrimaryButtonLoadingAndTimerState(open val uiText: UiText) : DefaultState() {
    data class ButtonLoading(override val uiText: UiText = UiText.DynamicString("")) :
        PrimaryButtonLoadingAndTimerState(uiText)
    data class Idle(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) :
        PrimaryButtonLoadingAndTimerState(uiText)
    data class Timer(override val uiText: UiText = UiText.ResourceString(R.string.confirm)) :
        PrimaryButtonLoadingAndTimerState(uiText)
}

@Composable
fun SimpleTextButton(
    modifier: Modifier = Modifier,
    title: String = "",
    enable: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector? = null, // پارامتر آیکون اضافه شد
    onButtonClick: () -> Unit
) {
    TextButton(
        modifier = modifier.height(56.dp),
        enabled = enable,
        onClick = onButtonClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (enable) textColor else MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (enable) textColor else MaterialTheme.colorScheme.tertiaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: ImageVector? = null,
    onButtonClick: () -> Unit,
) {
    val context = LocalContext.current
    AppTheme {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
                .height(56.dp),
            onClick = { onButtonClick.invoke() },
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = when (buttonState) {
                is ButtonState.Enable -> true
                is ButtonState.Disable -> false
            }
        ) {
            val contentColor = when (buttonState) {
                is ButtonState.Enable -> textColor
                is ButtonState.Disable -> MaterialTheme.colorScheme.tertiary
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = buttonState.uiText.asString(context),
                    color = contentColor,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LinearLoadingPrimaryButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: ImageVector? = null, // پارامتر آیکون اضافه شد
    onLoadingButtonClick: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading_transition")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing)
        ),
        label = "loading_progress"
    )
    LaunchedEffect(Unit) {
        delay(5000)
        onLoadingButtonClick()
    }
    AppTheme() {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrimaryButton(
                modifier = modifier,
                buttonState = buttonState,
                color = color,
                textColor = textColor,
                icon = icon,
                onButtonClick = {
                    onLoadingButtonClick()
                }
            )
            Canvas(
                modifier = modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(12.dp))
            ) {
                val canvasWidth = size.width
                val fillWidth = canvasWidth * progress
                drawRect(
                    color = Color.White.copy(alpha = 0.3f),
                    size = Size(fillWidth, size.height)
                )
            }
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    color: Color? = null,
    textColor: Color? = null,
    icon: ImageVector? = null,
    onButtonClick: () -> Unit,
) {
    val context = LocalContext.current
    AppTheme {
        OutlinedButton(
            modifier = Modifier
                .then(modifier)
                .height(56.dp),
            onClick = { onButtonClick.invoke() },
            border = BorderStroke(
                1.dp,
                if (buttonState is ButtonState.Enable) (color ?: textColor
                ?: MaterialTheme.colorScheme.primary) else MaterialTheme.colorScheme.tertiaryContainer
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = when (buttonState) {
                is ButtonState.Enable -> true
                is ButtonState.Disable -> false
            },
        ) {
            val contentColor = when (buttonState) {
                is ButtonState.Enable -> textColor ?: MaterialTheme.colorScheme.primary
                is ButtonState.Disable -> MaterialTheme.colorScheme.tertiary
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = buttonState.uiText.asString(context),
                    color = contentColor,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ProgressiveLoadingButton(
    modifier: Modifier = Modifier,
    loadingButtonState: LoadingButtonState,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    onButtonClick: () -> Unit
) {
    when (loadingButtonState) {
        is LoadingButtonState.Idle -> {
            PrimaryButton(
                modifier = modifier,
                buttonState = ButtonState.Enable(loadingButtonState.uiText),
                onButtonClick = onButtonClick,
                color = color,
            )
        }
        is LoadingButtonState.LoadingButton -> {
            Box(modifier = modifier) {
                PrimaryButton(
                    buttonState = ButtonState.Disable(loadingButtonState.uiText),
                    onButtonClick = onButtonClick,
                )
                CustomCircleProgressBar(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(30.dp)
                )
            }
        }

        is LoadingButtonState.Disable -> {
            PrimaryButton(
                modifier = modifier,
                buttonState = ButtonState.Disable(loadingButtonState.uiText),
                onButtonClick = onButtonClick,
            )
        }
    }
}

@Composable
fun TimerSecondaryButton(
    modifier: Modifier,
    timerState: PrimaryButtonLoadingAndTimerState,
    totalSeconds: Int,
    icon: ImageVector? = null,
    onButtonClick: () -> Unit,
    onTimerFinished: () -> Unit,
) {
    AppTheme {
        Box() {
            var buttonPrimaryState = DefaultState()

            when (timerState) {
                is PrimaryButtonLoadingAndTimerState.ButtonLoading -> {
                    buttonPrimaryState =
                        ButtonState.Disable(UiText.ResourceString(R.string.please_wait))
                }
                is PrimaryButtonLoadingAndTimerState.Timer -> {
                    var timeLeft by remember { mutableStateOf(totalSeconds) }
                    val isCounting = timeLeft > 0
                    LaunchedEffect(timeLeft) {
                        if (isCounting) {
                            delay(1_000)
                            timeLeft--
                        } else {
                            onTimerFinished.invoke()
                        }
                    }
                    buttonPrimaryState =
                        ButtonState.Disable(UiText.DynamicString(timeLeft.toString()))
                }
                is PrimaryButtonLoadingAndTimerState.Idle -> {
                    buttonPrimaryState = ButtonState.Enable(timerState.uiText)
                }
            }
            SecondaryButton(
                modifier = modifier,
                buttonState = buttonPrimaryState,
                icon = icon, // ارسال آیکون
                onButtonClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SampleLinearProgressiveLoadingButton() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            SecondaryButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                buttonState = ButtonState.Enable(),
                onButtonClick = {}
            )

            SecondaryButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                buttonState = ButtonState.Disable(),
                onButtonClick = {}
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            PrimaryButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                buttonState = ButtonState.Enable(),
                onButtonClick = {}
            )

            PrimaryButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                buttonState = ButtonState.Disable(),
                onButtonClick = {}
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            SimpleTextButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                title = "Simple Text Button",
                modifier = Modifier.padding(16.dp),
                enable = true,
                onButtonClick = {
                }
            )

            SimpleTextButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                title = "Simple Text Button",
                enable = false,
                onButtonClick = {
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            LinearLoadingPrimaryButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_done_24),
                buttonState = ButtonState.Enable(UiText.DynamicString("بازگشت")),
                onLoadingButtonClick = {
                }
            )


            ProgressiveLoadingButton(
                loadingButtonState = LoadingButtonState.LoadingButton
            ) { }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                thickness = 4.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

        }
    }
}