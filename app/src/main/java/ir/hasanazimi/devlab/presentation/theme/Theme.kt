package ir.hasanazimi.devlab.presentation.theme


import android.app.Activity
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import ir.hasanazimi.devlab.R

private val YekanBakhGroup = FontFamily(
    Font(R.font.yekan_thin, FontWeight.Thin),    // W100
    Font(R.font.yekan_light, FontWeight.ExtraLight),      // W200
    Font(R.font.yekan_regular, FontWeight.Normal),        // W400
    Font(R.font.yekan_semi_bold, FontWeight.Medium),      // W500
    Font(R.font.yekan_bold, FontWeight.Bold),             // W700
    Font(R.font.yekan_extra_bold, FontWeight.ExtraBold),  // W800
    Font(R.font.yekan_black, FontWeight.Black),           // W900
    Font(R.font.yekan_extra_black, FontWeight.Black)      // W900
)

private val CustomTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Thin,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    displayMedium = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Light,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    displaySmall = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),

    headlineLarge = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    headlineMedium = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    headlineSmall = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),

    titleLarge = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    titleMedium = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    titleSmall = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),

    bodyLarge = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    bodyMedium = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    bodySmall = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),

    labelLarge = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    labelMedium = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    ),
    labelSmall = TextStyle(
        fontFamily = YekanBakhGroup,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        textDirection = TextDirection.Rtl,
        textAlign = TextAlign.Start
    )
)


/*----------------------------------------------------------------------------------------------------------------------------*/
private val primary = Color(0xFF0c243e)
private val onPrimary = Color(0xFFFFFFFF)
private val primaryContainer = Color(0xFFECF1F8)
private val onPrimaryContainer = Color(0xFF001A30)

private val inversePrimary = Color(0xFFE3824A)

private val secondary = Color(0xFF15a6e5)
private val onSecondary = Color(0xFF0c243e)
private val secondaryContainer = Color(0xFFF0F9FF)
private val onSecondaryContainer = Color(0xFF001F2C)

private val tertiary = Color(0xFF424242)
private val onTertiary = Color(0xFFFFFFFF)
private val tertiaryContainer = Color(0xFFE8E8E8)
private val onTertiaryContainer = Color(0xFF191919)

val background = Color(0xFFf8faf9)
val onBackground = Color(0xFF1A1A1A)

private val error = Color(0xFF9A112C)
private val onError = Color(0xFFFFFFFF)
private val errorContainer = Color(0xFFFFEBEE)
private val onErrorContainer = Color(0xFF410002)

/*----------------------------------------------------------------------------------------------------------------------------*/

private val lightColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    inversePrimary = inversePrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    background = background,
    onBackground = onBackground,
    surface = background,
    onSurface = onBackground,

    surfaceVariant = Color(0xFFF0F4F9),
    onSurfaceVariant = Color(0xFF43474E),
    surfaceTint = primary,
    inverseSurface = Color(0xFF2E3132),
    inverseOnSurface = Color(0xFFF0F1F3),

    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,

    outline = Color(0xFF74777F),
    outlineVariant = Color(0xFFC4C6CF),
    scrim = Color.Black,

    surfaceBright = Color(0xFFFCFDFC),
    surfaceContainer = Color(0xFFF3F4F6),
    surfaceContainerHigh = Color(0xFFECECEF),
    surfaceContainerHighest = Color(0xFFE6E8EA),
    surfaceContainerLow = Color(0xFFF8F9FA),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceDim = Color(0xFFDADDDD),
)


















fun Context.findActivity(): Activity? {
    var ctx = this
    while (ctx is android.content.ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}



@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    layoutDirection: LayoutDirection = LayoutDirection.Rtl,
    fixedFontScale: Boolean = true,
    content: @Composable () -> Unit
) {

    val currentDensity = LocalDensity.current
    val densityToUse = if (fixedFontScale) {
        Density(currentDensity.density, fontScale = 1f)
    } else currentDensity

    val view = LocalView.current
    val activity = LocalContext.current.findActivity()

    SideEffect {
        activity?.let {
            it.window.statusBarColor = Color.Transparent.toArgb()
            val controller = WindowInsetsControllerCompat(it.window, view)
            controller.isAppearanceLightStatusBars = true
        }
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
        LocalDensity provides densityToUse
    ) {
        MaterialTheme(
            typography = CustomTypography,
            colorScheme = lightColorScheme,
            content = content
        )
    }
}





