package ir.hasanazimi.android_compose_lab.presentation.ui_kit.switch_toggle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.android_compose_lab.R

enum class SwitchState {
    Unchecked,
    Checked,
    DisabledChecked,
    DisabledUnchecked,
    Loading
}

@Composable
fun SmartSwitch(
    modifier: Modifier = Modifier,
    state: SwitchState,
    onCheckedChange: (SwitchState) -> Unit
) {

    when (state) {

        SwitchState.Loading -> {
            Switch(
                checked = false,
                enabled = true,
                onCheckedChange = { onCheckedChange.invoke(SwitchState.Checked) },
                modifier = modifier
            )
            Box(
                modifier = Modifier.padding(top = 8.8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = modifier.width(52.dp).height(30.dp).fillMaxSize().background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(16.dp)).padding(all = 8.dp),
                    contentScale = ContentScale.Inside,
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "loading"
                )
            }

        }

        SwitchState.Unchecked -> {
            Switch(
                checked = false,
                enabled = true,
                onCheckedChange = { onCheckedChange.invoke(SwitchState.Checked) },
                modifier = modifier
            )
        }

        SwitchState.Checked -> {
            Switch(
                checked = true,
                enabled = true,
                onCheckedChange = { onCheckedChange(SwitchState.Unchecked) },
                modifier = modifier,
                thumbContent = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_done_24),
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            )
        }

        SwitchState.DisabledChecked -> {
            Switch(
                checked = true,
                enabled = false,
                onCheckedChange = {  },
                modifier = modifier,
                thumbContent = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_done_24),
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            )
        }

        SwitchState.DisabledUnchecked -> {
            Switch(
                checked = false,
                enabled = false,
                onCheckedChange = { },
                modifier = modifier
            )
        }
    }
}




@Preview
@Composable
private fun Unchecked() {
    SmartSwitch(
        state = SwitchState.Unchecked,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun Checked() {
    SmartSwitch(
        state = SwitchState.Checked,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun DisabledUnchecked() {
    SmartSwitch(
        state = SwitchState.DisabledUnchecked,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun DisabledChecked() {
    SmartSwitch(
        state = SwitchState.DisabledChecked,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun Loading() {
    SmartSwitch(
        state = SwitchState.Loading,
        onCheckedChange = {}
    )
}
