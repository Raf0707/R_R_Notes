package raf.console.rrnotes.presentation.settings

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import raf.console.rrnotes.AppViewModel
import raf.console.rrnotes.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.recreate
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import raf.console.rrnotes.DataStoreManager
import raf.console.rrnotes.MainActivity
import raf.console.rrnotes.presentation.locale.LocaleViewModel
import raf.console.rrnotes.utils.LanguageOption
import raf.console.rrnotes.utils.ThemeOption
import java.util.Locale

@Composable
fun SettingsScreen(
    isDynamicColorsEnabled: Boolean,
    onDynamicColorsToggle: (Boolean) -> Unit,
    selectedTheme: ThemeOption,
    onThemeChange: (ThemeOption) -> Unit,
    contrastThemeEnabled: Boolean,
    onContrastThemeToggle: (Boolean) -> Unit,
    selectedLanguage: String,
    onLanguageChange: (Locale) -> Unit, // New parameter for language change
    onBackClick: () -> Unit // Added parameter for back button
) {

    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // Upper block with back button and title
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = stringResource(R.string.settings),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        item {
            // General Settings
            Text(
                text = "General Settings",
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }


        item {
            // Dynamic Colors Option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Dynamic Colors", fontSize = 18.sp)
                    Text(
                        text = if (isDynamicColorsEnabled) "Dynamic colors are currently enabled." else "Dynamic colors are currently disabled.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                Switch(
                    checked = isDynamicColorsEnabled,
                    onCheckedChange = onDynamicColorsToggle
                )
            }
        }


        item {
            // Theme Settings
            Text(
                text = "Theme Settings",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }

        item {
            ThemeOptionItem(
                title = "System Default",
                selected = selectedTheme == ThemeOption.SystemDefault,
                onClick = { onThemeChange(ThemeOption.SystemDefault) }
            )
        }

        item {
            ThemeOptionItem(
                title = "Light Theme",
                selected = selectedTheme == ThemeOption.Light,
                onClick = { onThemeChange(ThemeOption.Light) }
            )
        }

        item {
            ThemeOptionItem(
                title = "Dark Theme",
                selected = selectedTheme == ThemeOption.Dark,
                onClick = { onThemeChange(ThemeOption.Dark) }
            )
        }


        item {
            // Contrast Theme Option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.ColorLens, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "High Contrast Theme", fontSize = 18.sp)
                }
                Switch(
                    checked = contrastThemeEnabled,
                    onCheckedChange = onContrastThemeToggle
                )
            }
        }


        item {
            // Language Settings
            Text(
                text = "Language Settings",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }


        // Language Selection Screen
        item {
            LanguageSelectionScreen(
                //selectedLanguage = selectedLanguage,
                onLanguageChange = onLanguageChange
            )
        }





    }
}



/*@Composable
fun LanguageOptionItem(language: Locale, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = language.getDisplayLanguage(language), style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Select ${language.getDisplayLanguage(language)} as your language",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}*/

@Composable
fun LanguageOptionItem(
    language: Locale,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val languageMap = mapOf(
        Locale("ru") to "Русский",
        Locale("be") to "Беларуская",
        Locale("en") to "English",
        Locale("de") to "Deutsch",
        Locale("fr") to "Français",
        Locale("pl") to "Polski",
        Locale("es") to "Español",
        Locale("et") to "Eesti",
        Locale("it") to "Italiano",
        Locale("el") to "Ελληνικά",
        Locale("hi") to "हिन्दी",
        Locale("fa") to "فارسی",
        Locale("ar") to "العربية",
        Locale("ar", "PS") to "العربية الفلسطينية",
        Locale("id") to "Bahasa Indonesia",
        Locale("he") to "עברית",
        Locale("yi") to "ייִדיש",
        Locale("tt") to "Татарча",
        Locale("ba") to "Башҡортса",
        Locale("kk") to "Қазақша",
        Locale("ky") to "Кыргызча",
        Locale("tg") to "Тоҷикӣ",
        Locale("uz") to "Oʻzbekcha",
        Locale("hy") to "Հայերեն",
        Locale("az") to "Azərbaycan dili"
    )

    val languageName = languageMap[language] ?: language.getDisplayLanguage(language)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = languageName, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Select $languageName as your language",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}


@Composable
fun ThemeOptionItem(title: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = when (title) {
                    "System Default" -> "Use system default theme"
                    "Light Theme" -> "Use light theme"
                    else -> "Use night theme"
                },
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}

// ViewModel usage
@Composable
fun SettingsScreenWithViewModel(appViewModel: AppViewModel, navController: NavController) {
    val dynamicColorsEnabled by appViewModel.dynamicColor.collectAsState()
    val contrastThemeEnabled by appViewModel.contrastTheme.collectAsState()
    val selectedTheme by appViewModel.theme.collectAsState()
    val selectedLanguage by appViewModel.language.collectAsState()

    SettingsScreen(
        isDynamicColorsEnabled = dynamicColorsEnabled,
        onDynamicColorsToggle = { appViewModel.setDynamicColor(it) },
        selectedTheme = ThemeOption.valueOf(selectedTheme.toString()),
        onThemeChange = { appViewModel.setTheme(it) },
        contrastThemeEnabled = contrastThemeEnabled,
        onContrastThemeToggle = { appViewModel.setContrastTheme(it) },
        selectedLanguage = selectedLanguage.toString(),
        onLanguageChange = { appViewModel.setLanguage(it) },
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun LanguageSelectionScreen(
    onLanguageChange: (Locale) -> Unit
) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()
    val appViewModel: AppViewModel = viewModel()

    // Загружаем сохранённый язык
    //var selectedLanguage by remember { mutableStateOf(Locale("ru")) }
    //val selectedLanguage by appViewModel.selectedLanguage.collectAsState()
    val selectedLanguage by appViewModel.selectedLanguage.collectAsState(initial = Locale("ru"))

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = stringResource(R.string.language_settings),
            style = MaterialTheme.typography.headlineMedium)

        // Отображаем все доступные языковые опции
        listOf(
            Locale("ru") to "Русский",
            Locale("be") to "Беларуская",
            Locale("en") to "English", //en-GB
            Locale("de") to "Deutsch",
            Locale("fr") to "Français",
            Locale("pl") to "Polski",
            Locale("es") to "Español",
            Locale("et") to "Eesti",
            Locale("it") to "Italiano",
            Locale("el") to "Ελληνικά",
            Locale("hi") to "हिन्दी",
            Locale("fa") to "فارسی",
            Locale("ar") to "العربية",
            Locale("ar", "PS") to "العربية الفلسطينية",
            Locale("id") to "Bahasa Indonesia",
            Locale("he") to "עברית", //iw
            Locale("yi") to "ייִדיש",
            Locale("tt") to "Татарча",
            Locale("ba") to "Башҡортса",
            Locale("kk") to "Қазақша",
            Locale("ky") to "Кыргызча",
            Locale("tg") to "Тоҷикӣ",
            Locale("uz") to "Oʻzbekcha",
            Locale("hy") to "Հայերեն",
            Locale("az") to "Azərbaycan dili"
        ).forEach { (locale, languageName) ->
            LanguageOptionItem(
                locale, // Передаём Locale
                isSelected = selectedLanguage == locale,
                onClick = {
                    coroutineScope.launch {
                        appViewModel.setLanguage(locale, context)
                        onLanguageChange(locale)
                        (context as? Activity)?.recreate()
                    }
                }
            )
        }
    }
    Spacer(
        modifier = Modifier.padding(bottom = 64.dp)
    )
}