package raf.console.rrnotes.presentation.locale

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import raf.console.rrnotes.AppViewModel
import java.util.Locale

@JvmOverloads
@Composable
fun LocaleAwareApp(
    viewModel: LocaleViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val locale by viewModel.currentLocale.collectAsState()

    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    DisposableEffect(locale) {
        val newConfig = Configuration(configuration)
        newConfig.setLocale(locale)
        context.createConfigurationContext(newConfig)

        onDispose { }
    }

    CompositionLocalProvider(LocaleConfiguration provides locale) {
        content()
    }
}

val LocaleConfiguration = compositionLocalOf { Locale.getDefault() }
