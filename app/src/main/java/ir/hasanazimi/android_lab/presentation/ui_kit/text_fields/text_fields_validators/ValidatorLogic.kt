package ir.hasanazimi.android_lab.presentation.ui_kit.text_fields.text_fields_validators

import ir.hasanazimi.android_lab.R
import ir.hasanazimi.android_lab.common.base.helpers.extension_helpers.isPersian
import ir.hasanazimi.android_lab.common.base.helpers.extension_helpers.keepOnlyNumbers
import ir.hasanazimi.android_lab.presentation.ui_kit.text_fields.UiText
import java.text.NumberFormat
import java.util.Locale
import java.util.regex.Pattern


/**--------------------------------- Validator INTERFACE (Strategy) -----------------------------*/
interface Validator<T> {
    fun validate(data: T): ValidationResult<T>
}


/**-------------------------------------- IMPLEMENTATIONS ---------------------------------------*/
object RegularValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        return ValidationResult.Typing(data)
    }
}

object PersianUserNameValidator : Validator<InputData> {

    override fun validate(data: InputData): ValidationResult<InputData> {

        val typingResult = ValidationResult.Typing(data)

        val input = data.stringInput

        return when {
            input.isBlank() -> ValidationResult.Invalid(
                data,
                UiText.DynamicString("نام نمی‌تواند خالی باشد")
            )

            isPersian(input).not() -> ValidationResult.Invalid(
                data,
                UiText.DynamicString("فقط حروف فارسی مجاز است")
            )

            else -> ValidationResult.Valid(
                data,
                supportingText = "نام معتبر است"
            )
        }
    }
}

/*--------------------------------------------------------------------------*/
object PersianDatePickerValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        return ValidationResult.Typing(data)
    }
}
/*--------------------------------------------------------------------------*/
object EmailValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val regex = Regex("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")
        return if (regex.matches(data.stringInput)) {
            ValidationResult.Valid(data)
        }
        else {
            ValidationResult.Invalid(data, UiText.ResourceString(R.string.invalid_email))
        }
    }
}
/*--------------------------------------------------------------------------*/

object MobileValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val numericInput = keepOnlyNumbers(data.stringInput)
        val isValid = keepOnlyNumbers(numericInput).matches(Regex("^0?9\\d{9}$"))
        return if (numericInput.isEmpty() || numericInput.length < 2 && numericInput.startsWith("0") || numericInput.length < 2 && numericInput.startsWith(
                "9"
            )
        ) {
            ValidationResult.Typing(InputData(numericInput, data.isNetworkError))
        } else if (!isValid && numericInput.length <= 10 && numericInput.startsWith("9") || !isValid && numericInput.length <= 11 && numericInput.startsWith(
                "09"
            )
        ) {
            ValidationResult.Typing(InputData(numericInput, data.isNetworkError))
        } else if (!isValid && !numericInput.startsWith("09") || !isValid && !numericInput.startsWith(
                "9"
            )
        ) {
            ValidationResult.Invalid(
                InputData(numericInput, data.isNetworkError),
                UiText.ResourceString(R.string.mobile_hint_error_message)
            )
        } else if (isValid && numericInput.length <= 11) {
            ValidationResult.Valid(InputData(numericInput, data.isNetworkError))
        } else
            ValidationResult.Typing(InputData(numericInput, data.isNetworkError))
    }
}
/*--------------------------------------------------------------------------*/
object NationalCodeValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val nationalCode = data.stringInput
        val result = try {
            if (nationalCode.length != 10) {
                false
            } else {
                //Check for equal numbers
                val allDigitEqual = arrayOf(
                    "0000000000",
                    "1111111111",
                    "2222222222",
                    "3333333333",
                    "4444444444",
                    "5555555555",
                    "6666666666",
                    "7777777777",
                    "8888888888",
                    "9999999999"
                )
                if (allDigitEqual.contains(nationalCode)) false
                else if (convertPersianNumberToEnglishAndRemoveZeroAtIndex0(nationalCode).isEmpty()) false
                else if (nationalCode.substring(3, 7) == "****") true
                else {
                    var sum = 0
                    for (i in 0 until 9) sum += nationalCode[i].toString().toInt() * (10 - i)

                    val lastNumber = nationalCode[9].toString().toInt()
                    val check = sum % 11
                    if (check < 2) lastNumber == check else lastNumber + check == 11
                }
            }
        } catch (e: Exception) {
            false
        }
        return if (result) ValidationResult.Valid(
            InputData(
                nationalCode,
                data.isNetworkError
            )
        ) else ValidationResult.Invalid(
            InputData(nationalCode, data.isNetworkError),
            UiText.ResourceString(R.string.national_code_is_not_valid)
        )
    }
}

/*--------------------------------------------------------------------------*/
object PasswordValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val input = data.stringInput
        val pattern = Pattern.compile("[0-9]{4,8}")
        val matcher = pattern.matcher(input)
        val isValid = matcher.matches()
        return if (isValid) ValidationResult.Valid(
            InputData(
                input,
                data.isNetworkError
            )
        ) else ValidationResult.Invalid(
            InputData(input, data.isNetworkError),
            UiText.ResourceString(R.string.password_invalid_error_message)
        )
    }
}

/*--------------------------------------------------------------------------*/
object OtpValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val tempOfData1 = data.copy(stringInput = keepOnlyNumbers(data.stringInput))
        val isValid = tempOfData1.stringInput.length == 4
        return if (isValid && data.isNetworkError.not()) {
            ValidationResult.Valid(
                InputData(
                    tempOfData1.stringInput,
                    data.isNetworkError
                )
            )
        } else if (isValid && data.isNetworkError) {
            ValidationResult.Invalid(
                data= InputData(
                    tempOfData1.stringInput,
                    data.isNetworkError
                ), UiText.DynamicString("")
            )
        } else ValidationResult.Typing(InputData(tempOfData1.stringInput, data.isNetworkError))
    }
}
/*--------------------------------------------------------------------------*/
object PostalCodeValidator : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {
        val postalCode = data.stringInput
        var isValid = true
        // Rule 1: The number of postal code characters should be 10 digits.
        if (postalCode.length != 10) {
            isValid = false
        }
        // Rule 2: In the first five digits of the postal code, 0 and 2 are not valid.
        val firstFive = postalCode.substring(0, 5)
        if (firstFive.contains('0') || firstFive.contains('2')) {
            isValid = false
        }
        // Rule 3: The fifth digit of the postal code cannot be 5.
        if (postalCode[4] == '5') {
            isValid = false
        }
        // Rule 4: The sixth digit of the postal code cannot be 0.
        if (postalCode[5] == '0') {
            isValid = false
        }
        // Rule 5: The last four digits of the postal code must be valid numbers (from 0 to 9).
        val lastFour = postalCode.substring(6)
        if (!lastFour.all { it.isDigit() }) {
            isValid = false
        }
        // Rule 6: Inserting "0000" in the last four digits of the postal code is invalid.
        if (lastFour == "0000") {
            isValid = false
        }
        // Rule 7: All repeated numbers at the end of the postal code are invalid, such as "1111111111".
        if (postalCode.all { it == postalCode[0] }) {
            isValid = false
        }
        return if (isValid) ValidationResult.Valid(
            InputData(
                postalCode,
                data.isNetworkError
            )
        ) else ValidationResult.Invalid(
            InputData(postalCode, data.isNetworkError),
            UiText.ResourceString(R.string.national_code_is_not_valid)
        )
    }
}
/*--------------------------------------------------------------------------*/
object Url : Validator<InputData> {
    override fun validate(data: InputData): ValidationResult<InputData> {

        val filteredText = data.stringInput.filter {
            it in 'a'..'z' ||
                    it in 'A'..'Z' ||
                    it in '0'..'9' ||
                    it == ':' ||
                    it == '.' ||
                    it == '-' ||
                    it == '/' ||
                    it == '\\'
        }

        val newData = data.copy(stringInput = filteredText)

        if (newData.stringInput.isBlank()) {
            return ValidationResult.Typing(newData)
        }

        if (newData.stringInput.length <= 8 && (
                    newData.stringInput.startsWith("http") ||
                            newData.stringInput.startsWith("https")
                    )
        ) {
            return ValidationResult.Typing(newData)
        }

        val urlPattern = Pattern.compile(
            "^((http|https)://)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(/\\S*)?$"
        )

        val isValid = urlPattern.matcher(newData.stringInput).matches()

        return if (isValid) {
            ValidationResult.Valid(newData)
        } else {
            ValidationResult.Invalid(
                data = newData,
                errorMessage = UiText.DynamicString("لینک نامعتبر است")
            )
        }
    }
}






/*------------------------------------ Validator Factory ----------------------------------------*/
object ValidatorFactory {
    fun getValidator(validationType: ValidationType): Validator<InputData> {
        return when (validationType) {
            is ValidationType.Regular -> RegularValidator
            is ValidationType.Mobile -> MobileValidator
            is ValidationType.Email -> EmailValidator
            is ValidationType.NationalCode -> NationalCodeValidator
            is ValidationType.PostalCode -> PostalCodeValidator
            is ValidationType.Password -> PasswordValidator
            is ValidationType.OtpCode -> OtpValidator
            is ValidationType.PersianDatePicker -> PersianDatePickerValidator
            is ValidationType.PersianUserName -> PersianUserNameValidator
            is ValidationType.Url -> Url
        }
    }
}




fun convertPersianNumberToEnglishAndRemoveZeroAtIndex0(it: String): String {
    val formatter = NumberFormat.getInstance(Locale.ENGLISH)
    return try {
        formatter.parse(keepOnlyNumbers(it)).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}