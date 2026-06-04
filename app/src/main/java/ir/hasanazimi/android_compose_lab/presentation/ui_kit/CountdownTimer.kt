package ir.hasanazimi.android_compose_lab.presentation.ui_kit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit


data class TimerState(
    var isActive: Boolean,
    var formattedTime: String
)



fun countdownTimer(totalSeconds: Int): Flow<TimerState> = flow {
    var timeLeft = totalSeconds
    while (timeLeft >= 0) {
        val minutes = TimeUnit.SECONDS.toMinutes(timeLeft.toLong())
        val secondsPart = timeLeft % 60
        val formattedTime = String.format("%02d:%02d", minutes, secondsPart)
        emit(
            TimerState(
                isActive = timeLeft > 0,
                formattedTime = formattedTime
            )
        )
        if (timeLeft == 0) {
            emit(
                TimerState(
                    isActive = false,
                    formattedTime = ""
                )
            )
            break
        }
        delay(1000L)
        timeLeft--
    }
}.flowOn(Dispatchers.Default)