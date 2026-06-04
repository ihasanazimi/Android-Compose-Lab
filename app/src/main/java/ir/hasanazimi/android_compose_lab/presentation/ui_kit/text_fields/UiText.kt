package ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val textValue: String) : UiText()
    data class ResourceString(@StringRes val resId: Int) : UiText()
}

fun UiText.asString(context: Context): String = when (this) {
    is UiText.DynamicString -> textValue
    is UiText.ResourceString -> context.getString(resId)
}

@Composable
fun UiText.asString(): String = when (this) {
    is UiText.DynamicString -> textValue
    is UiText.ResourceString -> stringResource(resId)
}