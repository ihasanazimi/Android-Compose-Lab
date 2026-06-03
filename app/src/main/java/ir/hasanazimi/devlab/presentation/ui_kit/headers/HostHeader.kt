package ir.hasanazimi.devlab.presentation.ui_kit.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.devlab.R
import ir.hasanazimi.devlab.presentation.theme.AppTheme

@Composable
fun HostHeader() {
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            color = Color.Transparent,
            shadowElevation = 4.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row() {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp , end = 1.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.baseline_error_outline_24),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HostHeaderPreview() {
    AppTheme {
        HostHeader()
    }
}