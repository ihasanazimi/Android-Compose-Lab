package ir.hasanazimi.android_lab.presentation.ui_kit

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun EndlessMarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    color: Color = MaterialTheme.colorScheme.tertiary
) {
    Text(
        text = text,
        modifier = modifier
            .basicMarquee(
                iterations = Int.MAX_VALUE,
                animationMode = MarqueeAnimationMode.Immediately,
                repeatDelayMillis = 0
            ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis, // برای فال‌بک (اگر مارکی غیرفعال بود)
        style = style,
        color = color
    )
}