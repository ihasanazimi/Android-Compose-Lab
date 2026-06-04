package ir.hasanazimi.zaghche.presentation.ui_kit.text_fields

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import ir.hasanazimi.android_compose_lab.R
import ir.hasanazimi.android_compose_lab.presentation.theme.AppTheme
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.InputConfigType
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.TextFieldConfig
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.TrailingIconType
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.asString
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.text_fields_validators.InputData
import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.text_fields_validators.ValidationResult

@Composable
fun BaseTextField(
    modifier: Modifier,
    value: String,
    enable: Boolean = true,
    config: TextFieldConfig,
    visualTransformation: VisualTransformation? = null,
    validatorResult: ValidationResult<InputData>?,
    onValueChanged: (typingData: InputData) -> Unit,
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
            TextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = config.textAlign),
                visualTransformation = visualTransformation
                    ?: if (config.keyboardType == KeyboardType.Password && config.isShowCharacter.not()) PasswordVisualTransformation(
                        mask = '•'
                    )
                    else if (config.keyboardType == KeyboardType.Password && config.isShowCharacter) VisualTransformation.None
                    else VisualTransformation.None,

                value = value,

                onValueChange = { newText ->
                    val processedText = if (newText.length <= config.maxCharacters) {
                        newText
                    } else {
                        newText.take(config.maxCharacters)
                    }

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
                supportingText = {
                    if (validatorResult is ValidationResult.Valid)
                        validatorResult.supportingText?.let { text ->
                            Text(text, style = MaterialTheme.typography.labelSmall)
                        }
                },
                singleLine = config.isSingleLine,
                maxLines = config.maxLines,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = config.keyboardType
                ),
                shape = config.shape,
                isError = isError,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedTextColor = config.textColor.invoke(),
                    unfocusedTextColor = config.textColor.invoke(),
                    errorTextColor = MaterialTheme.colorScheme.error,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedPlaceholderColor = config.textColor.invoke(),
                    unfocusedPlaceholderColor = config.textColor.invoke().copy(alpha = 0.3f),
                    errorIndicatorColor = MaterialTheme.colorScheme.error,
                    errorSupportingTextColor = MaterialTheme.colorScheme.error,
                    errorCursorColor = MaterialTheme.colorScheme.error,
                    errorTrailingIconColor = MaterialTheme.colorScheme.error,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                trailingIcon = when (val trailingType = config.trailingIconType) {
                    is TrailingIconType.None -> null
                    is TrailingIconType.ClearIcon -> {
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
                leadingIcon = config.leadingIcon,
            )
            if (isError) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun BaseTextFieldPreview() {
    BaseTextField(
        modifier = Modifier.padding(16.dp),
        value = "",
        config = TextFieldConfig.Companion.getConfig(InputConfigType.Mobile),
        validatorResult = null
    ) {}
}