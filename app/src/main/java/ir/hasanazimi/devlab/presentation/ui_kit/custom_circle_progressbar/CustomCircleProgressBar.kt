package ir.hasanazimi.devlab.presentation.ui_kit.custom_circle_progressbar

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.devlab.presentation.theme.AppTheme

@Composable
fun CustomCircleProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 6.dp,
        trackColor = MaterialTheme.colorScheme.secondary,
        strokeCap = StrokeCap.Round
    )
}


@Preview
@Composable
private fun CustomCircleProgressBarPreview() {
    AppTheme {
        CustomCircleProgressBar()
    }
}