package ir.hasanazimi.android_compose_lab.presentation.ui_kit.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.android_compose_lab.R
import ir.hasanazimi.android_compose_lab.presentation.theme.AppTheme


enum class GeneralHeaderEvent {
    BackBtnPressed,
    HelperBtnPressed
}

@Composable
fun GeneralHeader(
    modifier: Modifier = Modifier,
    title: String,
    showHelperIcon : Boolean = false,
    onAction: (GeneralHeaderEvent) -> Unit
) {

    AppTheme {
        val backIcon = R.drawable.angle_right
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .then(modifier)
        ) {


            IconButton(modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterStart), onClick = {
                onAction.invoke(GeneralHeaderEvent.BackBtnPressed)
            }) {
                Icon(
                    painter = painterResource(backIcon),
                    modifier = Modifier.size(24.dp).padding(4.dp),
                    contentDescription = "Back Preseed action",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(end = 16.dp)
            )

            if (showHelperIcon){
                val helperIcon = R.drawable.baseline_error_outline_24
                Image(
                    painter = painterResource(helperIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(horizontal = 16.dp)
                        .clickable {
                            onAction.invoke(GeneralHeaderEvent.HelperBtnPressed)
                        }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomeHeaderPreview() {
    AppTheme {
        GeneralHeader(
            modifier = Modifier,
            title = "تایتل"){
        }
    }
}