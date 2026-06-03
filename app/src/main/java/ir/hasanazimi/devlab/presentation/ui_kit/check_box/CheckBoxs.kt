package ir.hasanazimi.devlab.presentation.ui_kit.check_box

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.devlab.presentation.theme.AppTheme


data class CheckBoxConfig(val title : String, var isChecked : Boolean, val isEnabled : Boolean = true)

@Composable
fun EwanoCheckBox(modifier : Modifier = Modifier, config: CheckBoxConfig , onCheckedChange : (Boolean) -> Unit) {
    EwanoCheckBoxContent(modifier , config){
        onCheckedChange.invoke(it)
    }
}



@Composable
private fun EwanoCheckBoxContent(modifier : Modifier = Modifier,config  : CheckBoxConfig , onCheckedChange : (Boolean) -> Unit ){
    AppTheme {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.background).then(modifier) ,verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                    checkmarkColor = MaterialTheme.colorScheme.surface,
                    disabledCheckedColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledUncheckedColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                checked = config.isChecked , enabled = config.isEnabled , onCheckedChange = { onCheckedChange.invoke(it) })
            Text(text = config.title, style = MaterialTheme.typography.labelSmall)
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CheckBoxPreview() {

    EwanoCheckBoxContent(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        config = CheckBoxConfig(
            title = "سلام این یک متن تستی می باشد",
            isChecked = true,
        )
    ){
        Log.d("TAG", "CheckBoxPreview: $it")
    }

}