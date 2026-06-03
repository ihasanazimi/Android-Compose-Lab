package ir.hasanazimi.android_lab.presentation.ui_kit.text_fields

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.android_lab.presentation.theme.AppTheme
import ir.hasanazimi.android_lab.presentation.ui_kit.text_fields.text_fields_validators.InputData
import ir.hasanazimi.android_lab.presentation.ui_kit.text_fields.text_fields_validators.ValidationResult

@Composable
fun BaseOutlineTextField(
    modifier: Modifier,
    value: String,
    enable: Boolean = true,
    config: TextFieldConfig,
    validatorResult: ValidationResult<InputData>?,
    onValueChanged: (typingValue: InputData) -> Unit,
) {
    val context = LocalContext.current
    var isError = false
    var errorMessage = ""

    when (validatorResult) {
        is ValidationResult.Valid -> {
            isError = false
            errorMessage = ""
        }
        is ValidationResult.Invalid -> {
            isError = true
            errorMessage = validatorResult.errorMessage.asString(context)
        }
        is ValidationResult.Typing -> {
            isError = false
            errorMessage = ""
        }
        else -> {}
    }

    val focusManager = LocalFocusManager.current
    if (enable.not()) focusManager.clearFocus()

    AppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .then(modifier)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = config.textAlign),
                visualTransformation = if (config.keyboardType == KeyboardType.Password && config.isShowCharacter.not()) {
                    PasswordVisualTransformation(mask = '•')
                } else if (config.keyboardType == KeyboardType.Password && config.isShowCharacter) {
                    VisualTransformation.None
                } else {
                    VisualTransformation.None
                },
                // Use the raw value directly to ensure proper state synchronization for Paste/Cut
                value = value,
                onValueChange = { newText ->
                    // Handle max characters limit properly for Paste actions
                    val processedText = if (newText.length <= config.maxCharacters) {
                        newText
                    } else {
                        newText.take(config.maxCharacters)
                    }

                    // Only update if the text has actually changed to prevent unnecessary recompositions
                    if (processedText != value) {
                        onValueChanged(InputData(processedText))
                    }
                },
                enabled = enable,
                readOnly = enable.not(),
                label = config.label?.let {
                    {
                        Text(
                            it,
                            Modifier.padding(bottom = 4.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                placeholder = config.placeholder?.let {
                    {
                        Text(
                            it,
                            color = config.textColor.invoke().copy(alpha = 0.3f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                singleLine = config.isSingleLine,
                maxLines = config.maxLines,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = config.keyboardType
                ),
                shape = config.shape,
                isError = isError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = config.textColor.invoke(),
                    unfocusedTextColor = config.textColor.invoke(),
                    errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                    errorTextColor = MaterialTheme.colorScheme.error,
                    errorBorderColor = MaterialTheme.colorScheme.error,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledBorderColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                trailingIcon = when (val trailingType = config.trailingIconType) {
                    is TrailingIconType.None -> null
                    is TrailingIconType.ClearIcon -> {
                        // Check 'value' instead of 'limitedText' since limitedText is removed
                        if (value.isNotEmpty()) {
                            if (enable) {
                                {
                                    IconButton(
                                        onClick = {
                                            focusManager.clearFocus()
                                            onValueChanged(InputData())
                                        }
                                    ) {
                                        Image(
                                            modifier = Modifier.size(28.dp),
                                            painter = painterResource(id = R.drawable.ic_clear),
                                            contentDescription = "clearText"
                                        )
                                    }
                                }
                            } else null
                        } else null
                    }
                    is TrailingIconType.CustomIcon -> {
                        if (enable) {
                            {
                                trailingType.iconComposable {
                                    focusManager.clearFocus()
                                }
                            }
                        } else null
                    }
                },
                leadingIcon = config.leadingIcon
            )
            if (isError) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BaseTextFieldPreview() {
    AppTheme {
        Column {
            /** Simple **/
            BaseOutlineTextField(
                modifier = Modifier.padding(16.dp),
                value = "تسسسسسسسست",
                enable = true,
                config = TextFieldConfig.Companion.getConfig(InputConfigType.Mobile),
                validatorResult = null
            ) {}
        }
    }
}