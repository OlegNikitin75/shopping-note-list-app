package alex.dev.shoppingnotelistapp.presentation.ui.components.theme

import alex.dev.shoppinglistapp.presentation.ui.theme.AppDark
import alex.dev.shoppinglistapp.presentation.ui.theme.AppDarkSlateTeal
import alex.dev.shoppinglistapp.presentation.ui.theme.AppGray
import alex.dev.shoppinglistapp.presentation.ui.theme.AppGreen
import alex.dev.shoppinglistapp.presentation.ui.theme.AppLightGray
import alex.dev.shoppinglistapp.presentation.ui.theme.AppWhite
import alex.dev.shoppinglistapp.presentation.ui.theme.Pink40
import alex.dev.shoppinglistapp.presentation.ui.theme.Pink80
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AppGreen,
    onPrimary = AppWhite,
    secondary = AppGray,
    tertiary = Pink80,
    background = AppDark,
    onBackground = AppWhite,
    surface = AppDarkSlateTeal,
    onSurface = AppWhite,
)
private val LightColorScheme = lightColorScheme(
    primary = AppGreen,
    onPrimary = AppWhite,
    secondary = AppGray,
    tertiary = Pink40,
    background = AppWhite,
    onBackground = AppDark,
    surface = AppLightGray,
    onSurface = AppDark,
    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ShoppingNoteListAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}