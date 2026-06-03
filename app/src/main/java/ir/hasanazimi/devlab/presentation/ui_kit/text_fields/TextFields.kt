package ir.hasanazimi.devlab.presentation.ui_kit.text_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.devlab.presentation.theme.AppTheme
import ir.hasanazimi.devlab.presentation.ui_kit.text_fields.text_fields_validators.InputData
import ir.hasanazimi.devlab.presentation.ui_kit.text_fields.text_fields_validators.ValidationResult
import ir.hasanazimi.devlab.presentation.ui_kit.text_fields.text_fields_validators.ValidationType
import ir.hasanazimi.devlab.presentation.ui_kit.text_fields.text_fields_validators.ValidatorFactory
import ir.hasanazimi.zaghche.presentation.ui_kit.text_fields.BaseTextField


/** Mobile TextField ******************************************************************************/
@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    inputData: InputData,
    enable : Boolean = true,
    outlineStyle: Boolean = false,
    onValueChange: (data: ValidationResult<InputData>) -> Unit
) {

    val config = TextFieldConfig.getConfig(InputConfigType.Mobile)


    config.maxCharacters = if (inputData.stringInput.startsWith("9")) {
        10
    } else if (inputData.stringInput.startsWith("09")) {
        11
    } else {
        10
    }

    if (outlineStyle){
        BaseOutlineTextField(
            modifier = modifier,
            value = inputData.stringInput,
            enable = enable,
            config = config,
            validatorResult = ValidatorFactory.getValidator(validationType = ValidationType.Mobile)
                .validate(inputData)
        ) { typingData ->
            onValueChange(
                ValidatorFactory.getValidator(validationType = ValidationType.Mobile)
                    .validate(typingData)
            )
        }
    }else{
        BaseTextField(
            modifier = modifier,
            value = inputData.stringInput,
            enable = enable,
            config = config,
            validatorResult = ValidatorFactory.getValidator(validationType = ValidationType.Mobile)
                .validate(inputData)
        ) { typingData ->
            onValueChange(
                ValidatorFactory.getValidator(validationType = ValidationType.Mobile)
                    .validate(typingData)
            )
        }
    }
}





/** NationalCode TextField ******************************************************************************/
@Composable
fun NationalCodeTextField(
    modifier: Modifier = Modifier,
    inputData: InputData,
    enable : Boolean = true,
    outlineStyle: Boolean = false,
    onValueChange: (data: ValidationResult<InputData>) -> Unit
) {

    val config = TextFieldConfig.getConfig(InputConfigType.NationalCode)

    if (outlineStyle){
        BaseOutlineTextField(
            modifier = modifier,
            value = inputData.stringInput,
            enable = enable,
            config = config,
            validatorResult = ValidatorFactory.getValidator(validationType = ValidationType.NationalCode)
                .validate(inputData)
        ) { typingData ->
            onValueChange(
                ValidatorFactory.getValidator(validationType = ValidationType.NationalCode)
                    .validate(typingData)
            )
        }
    }else{
        BaseTextField(
            modifier = modifier,
            value = inputData.stringInput,
            enable = enable,
            config = config,
            validatorResult = ValidatorFactory.getValidator(validationType = ValidationType.NationalCode)
                .validate(inputData)
        ) { typingData ->
            onValueChange(
                ValidatorFactory.getValidator(validationType = ValidationType.NationalCode)
                    .validate(typingData)
            )
        }
    }
}


/** Regular TextField *****************************************************************************/
@Composable
fun RegularTextField(
    modifier: Modifier = Modifier,
    data: InputData,
    enable : Boolean = true,
    placeHolder : String = "",
    label : String = "",
    maxCharacters : Int = 32,
    isOutLine : Boolean = false,
    onValueChanged: (data: ValidationResult<InputData>) -> Unit
) {
    val config = TextFieldConfig.getConfig(InputConfigType.Regular)
    config.placeholder = placeHolder
    config.label = label
    config.maxCharacters = maxCharacters

    if (isOutLine){

        config.shape = RoundedCornerShape(8.dp)

        BaseOutlineTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            enable = enable,
            config = config,
            validatorResult = null
        ) { typingData ->
            /** Hey guys . this component always just return TYPING validatorResult :) */
            onValueChanged(
                ValidatorFactory.getValidator(ValidationType.Regular).validate(typingData)
            )
        }
    }else{
        BaseTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            config = config,
            enable = enable,
            validatorResult = null
        ) { typingData ->
            /** Hey guys . this component always just return TYPING validatorResult :) */
            onValueChanged(
                ValidatorFactory.getValidator(ValidationType.Regular).validate(typingData)
            )
        }
    }

}


/** PersianUserName TextField *****************************************************************************/
@Composable
fun PersianUserNameTextField(
    modifier: Modifier = Modifier,
    data: InputData,
    validatorResult : ValidationResult<InputData>? = null,
    enable: Boolean = true,
    placeHolder : String = "",
    isOutLine : Boolean = false,
    onValueChanged: (data: ValidationResult<InputData>) -> Unit
) {
    val config = TextFieldConfig.getConfig(InputConfigType.PersianUserName)
    config.placeholder = placeHolder

    if (isOutLine){

        config.shape = RoundedCornerShape(8.dp)

        BaseOutlineTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            enable = enable,
            config = config,
            validatorResult = validatorResult
        ) { typingData ->
            onValueChanged(
                ValidatorFactory.getValidator(ValidationType.PersianUserName).validate(typingData)
            )
        }
    }else{
        BaseTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            config = config,
            enable = enable,
            validatorResult = validatorResult
        ) { typingData ->
            onValueChanged(
                ValidatorFactory.getValidator(ValidationType.PersianUserName).validate(typingData)
            )
        }
    }

}





/** Url TextField *****************************************************************************/
@Composable
fun UrlTextField(
    modifier: Modifier = Modifier,
    data: InputData,
    enable : Boolean = true,
    isOutLine : Boolean = false,
    validatorResult: ValidationResult<InputData>?,
    onValueChanged: (data: ValidationResult<InputData>) -> Unit
) {
    val config = TextFieldConfig.getConfig(InputConfigType.Url)

    if (isOutLine){

        config.shape = RoundedCornerShape(8.dp)

        BaseOutlineTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            enable = enable,
            config = config,
            validatorResult = validatorResult
        ) { typingData ->
            onValueChanged(ValidatorFactory.getValidator(ValidationType.Url).validate(typingData))
        }
    }else{
        BaseTextField(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .then(modifier),
            value = data.stringInput,
            config = config,
            enable = enable,
            validatorResult = validatorResult
        ) { typingData ->
            /** Hey guys . this component always just return NONE validatorResult :) */
            onValueChanged(
                ValidatorFactory.getValidator(ValidationType.Url).validate(typingData)
            )
        }
    }

}


@Preview
@Composable
private fun TextFieldsPreview() {

    AppTheme {
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background)) {

            /* Regular TextField ------------------------------------------------------------------*/
            var regularData by remember { mutableStateOf(InputData("متن تستی", false)) }
            RegularTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                data = regularData,
                placeHolder = "RegularTextField Hint",
                label = "برچسب تکست فیلد معمولی",
                enable = true,
            ) { inputData -> }

            RegularTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                data = regularData,
                placeHolder = "RegularTextField Hint",
                label = "برچسب تکست فیلد معمولی",
                enable = true,
                isOutLine = true
            ) { inputData -> }





            /* Mobile TextField -------------------------------------------------------------------*/
            var mobileData by remember { mutableStateOf(InputData("09199209095", false)) }
            MobileTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                inputData = mobileData,
            ) { inputData -> }


            /* NationalCode TextField -------------------------------------------------------------------*/
            var nationalId by remember { mutableStateOf(InputData("4900608882", false)) }
            NationalCodeTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                inputData = nationalId,
            ) { inputData -> }

        }
    }
}