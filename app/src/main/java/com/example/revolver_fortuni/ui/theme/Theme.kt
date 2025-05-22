package com.example.revolver_fortuni.ui.theme

import android.app.Activity
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
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Revolver_fortuniTheme(
    darkTheme: Boolean = true, // Установите значение по умолчанию в true
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme // Используйте палитру темных цветов
    } else {
        LightColorScheme // Это не будет использоваться, но оставьте для возможности переключения
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}