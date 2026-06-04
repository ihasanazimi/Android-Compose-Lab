package ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.text_fields_validators

import ir.hasanazimi.android_compose_lab.presentation.ui_kit.text_fields.UiText


/*--------------------------------------------------------------------------*/

data class InputData(val stringInput: String = "", val isNetworkError: Boolean = false)

/*--------------------------------------------------------------------------*/

sealed class ValidationResult<out T>(val totally : T) {
    data class Valid<out T>(val data: T, val supportingText: String? = null) : ValidationResult<T>(data)
    data class Invalid<out T>(val data: T, val errorMessage: UiText) : ValidationResult<T>(data)
    class Typing<out T>(val data: T) : ValidationResult<T>(data)
}

/*--------------------------------------------------------------------------*/


sealed class ValidationType {
    object Regular : ValidationType()
    object PersianUserName : ValidationType()
    object Url : ValidationType()
    object PersianDatePicker : ValidationType()
    object Mobile : ValidationType()
    object Email : ValidationType()
    object NationalCode : ValidationType()
    object PostalCode : ValidationType()
    object Password : ValidationType()
    object OtpCode : ValidationType()
}