package ir.hasanazimi.devlab.presentation.ui_kit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.devlab.presentation.theme.AppTheme

@Composable
fun ResendOtpTimer(
    modifier: Modifier = Modifier,
    isTimerRunning: Boolean,
    initialSeconds: Int,
    activeTimerPostfix: String = "",
    finishedText: String = "ارسال مجدد",
    activeTimerStyle: TextStyle = MaterialTheme .typography.bodyLarge,
    finishedTextStyle: TextStyle = MaterialTheme .typography.bodyLarge.copy(MaterialTheme.colorScheme.primary),
    onTimerFinish: () -> Unit,
    onResendClick: () -> Unit
) {
    var formattedTime by remember { mutableStateOf("") }
    val currentOnTimerFinish by rememberUpdatedState(onTimerFinish)

    LaunchedEffect(key1 = isTimerRunning) {
        if (isTimerRunning) {
            countdownTimer(initialSeconds).collect { timerState ->
                formattedTime = timerState.formattedTime
                if (!timerState.isActive) {
                    currentOnTimerFinish()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth().then(modifier), contentAlignment = Alignment.Center) {
        if (isTimerRunning) {
            Text(
                text = "$formattedTime $activeTimerPostfix",
                style = activeTimerStyle
            )
        } else {
            Text(
                modifier = Modifier.clickable(onClick = onResendClick),
                text = finishedText,
                style = finishedTextStyle
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ResendOtpTimerPreview() {
    AppTheme {

        var isTimerMode by remember { mutableStateOf(true) }

        Box(modifier = Modifier.padding(16.dp)) {
            ResendOtpTimer(
                modifier = Modifier.padding(8.dp),
                isTimerRunning = isTimerMode,
                initialSeconds = 120,
                onTimerFinish = {
                    isTimerMode = false
                },
                onResendClick = {
                    isTimerMode = true
                }
            )
        }
    }
}