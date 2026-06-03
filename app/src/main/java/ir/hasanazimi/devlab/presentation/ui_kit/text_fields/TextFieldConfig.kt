package ir.hasanazimi.devlab.presentation.ui_kit.text_fields

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


sealed class TrailingIconType {
    object None : TrailingIconType()
    object ClearIcon : TrailingIconType()
    data class CustomIcon(
        val iconComposable: @Composable ((onIconClick: () -> Unit) -> Unit),
    ) : TrailingIconType()
}

sealed class InputConfigType {
    object Mobile : InputConfigType()
    object OTP : InputConfigType()
    object Regular : InputConfigType()
    object PersianUserName : InputConfigType()
    object Url : InputConfigType()
    object Password : InputConfigType()
    object Email : InputConfigType()
    object PostalCode : InputConfigType()
    object NationalCode : InputConfigType()
    object Amount: InputConfigType()
}


sealed class TextFieldConfig(
    var label: String? = "",
    var placeholder: String? = "",
    var isSingleLine: Boolean = true,
    var shape: Shape = RoundedCornerShape(topEnd = 12.dp , topStart = 12.dp , bottomEnd = 4.dp , bottomStart = 4.dp),
    var textColor: (@Composable () -> Color) = { MaterialTheme.colorScheme.onBackground },
    var maxLines: Int = Int.MAX_VALUE,
    var maxCharacters: Int = Int.MAX_VALUE,
    var keyboardType: KeyboardType = KeyboardType.Companion.Text,
    val textAlign: TextAlign = TextAlign.Start,
    val isShowCharacter: Boolean = true,
    var trailingIconType: TrailingIconType = TrailingIconType.ClearIcon,
    var leadingIcon: @Composable (() -> Unit)? = null
) {
    private object Mobile : TextFieldConfig(
        label = "شماره همراه",
        placeholder = "09121234567",
        isSingleLine = true,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        maxLines = 1,
        maxCharacters = 11,
        keyboardType = KeyboardType.Companion.Phone,
        textAlign = TextAlign.Start,
        isShowCharacter = true
    ) {
        init {
            Log.i("TAG", " init mobile object  ")
        }
    }

    private object OtpCode : TextFieldConfig(
        maxCharacters = 4,
        keyboardType = KeyboardType.Number,
        textAlign = TextAlign.Center,
        trailingIconType = TrailingIconType.None,
    )

    private object Regular : TextFieldConfig(
        isShowCharacter = true,
        keyboardType = KeyboardType.Text,
        trailingIconType = TrailingIconType.ClearIcon,
    )


    private object Email : TextFieldConfig(
        placeholder = "example@email.com",
        keyboardType = KeyboardType.Companion.Email,
        maxCharacters = 100,
        label = "ایمیل"
    )

    private object Password : TextFieldConfig(
        label = "رمز عبور",
        placeholder = "رمز عبور را وارد کنید",
        maxCharacters = 32,
        isShowCharacter = false
    )

    private object PostalCode : TextFieldConfig(
        label = "کد پستی",
        placeholder = "1234567890",
        maxCharacters = 10,
    )
    private object Amount: TextFieldConfig(
        label = "مبلغ دلخواه (ریال)",
        maxCharacters = 10,
        keyboardType = KeyboardType.Number,
        maxLines = 1
    )

    private object NationalCode : TextFieldConfig(
        label = "کد ملی",
        placeholder = "0012345678",
        isSingleLine = true,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        maxLines = 1,
        maxCharacters = 10,
        keyboardType = KeyboardType.Companion.Number,
        textAlign = TextAlign.Start,
        isShowCharacter = true
    ) {
        init {
            Log.i("TAG", " init mobile object  ")
        }
    }



    private object PersianUserName : TextFieldConfig(
        label = "نام",
        placeholder = "حسن , زهرا , امیر ، شایان و ..",
        isSingleLine = true,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        maxLines = 1,
        maxCharacters = 32,
        keyboardType = KeyboardType.Companion.Text,
        textAlign = TextAlign.Start,
        isShowCharacter = true
    )


    private object Url : TextFieldConfig(
        label = "لینک منبع را وارد نمائید",
        placeholder = "https://google.com/RSS",
        isSingleLine = true,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        maxLines = 1,
        maxCharacters = 200,
        keyboardType = KeyboardType.Companion.Ascii,
        textAlign = TextAlign.Start,
        isShowCharacter = true
    )


    companion object {
        fun getConfig(inputConfigType: InputConfigType): TextFieldConfig {
            return when (inputConfigType) {
                InputConfigType.OTP -> OtpCode
                InputConfigType.Mobile -> Mobile
                InputConfigType.Regular -> Regular
                InputConfigType.Email -> Email
                InputConfigType.Password -> Password
                InputConfigType.PostalCode -> PostalCode
                InputConfigType.NationalCode -> NationalCode
                InputConfigType.Amount -> Amount
                InputConfigType.PersianUserName -> PersianUserName
                InputConfigType.Url -> Url
            }

        }
    }



}
