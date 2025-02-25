package raf.console.rrnotes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import raf.console.rrnotes.presentation.about_app.AboutScreenViewModel
import raf.console.rrnotes.presentation.bookmark.BookmarkViewModel
import raf.console.rrnotes.presentation.detail.DetailAssistedFactory
import raf.console.rrnotes.presentation.home.HomeViewModel
//import raf.console.rrnotes.presentation.locale.LanguageSwitcher
import raf.console.rrnotes.presentation.locale.LocaleViewModel
import raf.console.rrnotes.presentation.navigation.NoteNavigation
import raf.console.rrnotes.presentation.navigation.Screens
import raf.console.rrnotes.presentation.navigation.navigateToSingleTop
import raf.console.rrnotes.presentation.screens.details.enums.TabScreen
import raf.console.rrnotes.ui.theme.NoteApplicationTheme
import raf.console.rrnotes.utils.ThemeOption
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var assistedFactory: DetailAssistedFactory


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var dataStoreManager: DataStoreManager = DataStoreManager(applicationContext)

        // Загружаем сохранённый язык
        lifecycleScope.launch {
            dataStoreManager.getLanguage().collect { savedLanguage ->
                updateAppLanguage(savedLanguage.toString())
                Locale.setDefault(savedLanguage)
            }
        }

        var savedLanguage = runBlocking {
            dataStoreManager.getLanguage().firstOrNull() ?: "ru"
        }

        updateAppLanguage(savedLanguage.toString(), this)

        setContent {
            val appViewModel: AppViewModel = viewModel()

            // Получаем настройки темы
            val selectedTheme by appViewModel.theme.collectAsState()
            val dynamicColorState by appViewModel.dynamicColor.collectAsState()
            val contrastThemeState by appViewModel.contrastTheme.collectAsState()
            val selectLanguage by appViewModel.language.collectAsState()
            // Определяем, должна ли быть тёмная тема

            val darkTheme = when (selectedTheme) {
                ThemeOption.Dark -> true
                ThemeOption.Light -> false
                else -> isSystemInDarkTheme()
            }

            Locale.setDefault(selectLanguage)

            // Загружаем сохранённый язык
            lifecycleScope.launch {
                dataStoreManager.getLanguage().collect { savedLanguage ->
                    updateAppLanguage(savedLanguage.toString())
                    Locale.setDefault(savedLanguage)
                }
            }
            updateAppLanguage(selectLanguage.language)
            //LanguageSwitcher(viewModel())
            //updateAppLanguage(selectLanguage.toString())

            LaunchedEffect(selectLanguage) {
                updateAppLanguage(selectLanguage.language, this@MainActivity)
            }

            NoteApplicationTheme(
                darkTheme = darkTheme,
                dynamicColor = dynamicColorState,
                contrastTheme = contrastThemeState
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    NoteApp()  // Основной контент
                }
            }



        }
    }

    private fun updateAppLanguage(language: String) {
        val locale = when (language) {
            "Русский" -> Locale.setDefault(Locale("ru", ))      // Russian
            "Беларуская" -> Locale.setDefault(Locale("be", ))      // Belarusian
            "English" -> Locale.setDefault(Locale("en"))       // English
            "Deutsch" -> Locale.setDefault(Locale("de"))         // German
            "Français" -> Locale.setDefault(Locale("fr"))      // French
            "Polski" -> Locale.setDefault(Locale("pl"))         // Polish
            "Español" -> Locale.setDefault(Locale("es"))        // Spanish
            "Eesti" -> Locale.setDefault(Locale("et"))        // Estonian
            "Italiano" -> Locale.setDefault(Locale("it"))      // Italian
            "Ελληνικά" -> Locale.setDefault(Locale("el"))        // Greek
            "हिन्दी" -> Locale.setDefault(Locale("hi"))         // Hindi
            "فارسی" -> Locale.setDefault(Locale("fa"))       // Persian
            "العربية" -> Locale.setDefault(Locale("ar"))         // Arabic (Standard)
            "العربية الفلسطينية" -> Locale.setDefault(Locale("ay", )) // Palestinian Arabic
            "Bahasa Indonesia" -> Locale.setDefault(Locale("id"))    // Indonesian
            "עברית" -> Locale.setDefault(Locale("he"))            // Hebrew
            "ייִדיש" -> Locale.setDefault(Locale("yi"))             // Yiddish
            "Татарча" -> Locale.setDefault(Locale("tt"))        // Tatar
            "Башҡортса" -> Locale.setDefault(Locale("ba"))       // Bashkir
            "Қазақша" -> Locale.setDefault(Locale("kk"))        // Kazakh
            "Кыргызча" -> Locale.setDefault(Locale("ky",))       // Kyrgyz
            "Тоҷикӣ" -> Locale.setDefault(Locale("tg"))       // Tajik
            "Oʻzbekcha" -> Locale.setDefault(Locale("uz"))        // Uzbek
            "Հայերեն" -> Locale.setDefault(Locale("hy"))        // Armenian
            "Azərbaycan dili" -> Locale.setDefault(Locale("az"))  // Azerbaijani
            else -> Locale.setDefault(Locale("ru", ))
        }
        //Locale.setDefault(Locale("ru"))
        /*val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)*/
    }

    private fun updateAppLanguage(language: String, context: Context) {
        val locale = when (language) {
            "Русский" -> Locale.setDefault(Locale("ru", ))      // Russian
            "Беларуская" -> Locale.setDefault(Locale("be", ))      // Belarusian
            "English" -> Locale.setDefault(Locale("en"))       // English
            "Deutsch" -> Locale.setDefault(Locale("de"))         // German
            "Français" -> Locale.setDefault(Locale("fr"))      // French
            "Polski" -> Locale.setDefault(Locale("pl"))         // Polish
            "Español" -> Locale.setDefault(Locale("es"))        // Spanish
            "Eesti" -> Locale.setDefault(Locale("et"))        // Estonian
            "Italiano" -> Locale.setDefault(Locale("it"))      // Italian
            "Ελληνικά" -> Locale.setDefault(Locale("el"))        // Greek
            "हिन्दी" -> Locale.setDefault(Locale("hi"))         // Hindi
            "فارسی" -> Locale.setDefault(Locale("fa"))       // Persian
            "العربية" -> Locale.setDefault(Locale("ar"))         // Arabic (Standard)
            "العربية الفلسطينية" -> Locale.setDefault(Locale("ay", )) // Palestinian Arabic
            "Bahasa Indonesia" -> Locale.setDefault(Locale("id"))    // Indonesian
            "עברית" -> Locale.setDefault(Locale("he"))            // Hebrew
            "ייִדיש" -> Locale.setDefault(Locale("yi"))             // Yiddish
            "Татарча" -> Locale.setDefault(Locale("tt"))        // Tatar
            "Башҡортса" -> Locale.setDefault(Locale("ba"))       // Bashkir
            "Қазақша" -> Locale.setDefault(Locale("kk"))        // Kazakh
            "Кыргызча" -> Locale.setDefault(Locale("ky",))       // Kyrgyz
            "Тоҷикӣ" -> Locale.setDefault(Locale("tg"))       // Tajik
            "Oʻzbekcha" -> Locale.setDefault(Locale("uz"))        // Uzbek
            "Հայերեն" -> Locale.setDefault(Locale("hy"))        // Armenian
            "Azərbaycan dili" -> Locale.setDefault(Locale("az"))  // Azerbaijani
            else -> Locale.setDefault(Locale("ru", ))
        }
        //Locale.setDefault(Locale("ru"))
        /*val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)*/
    }

    private fun updateAppLanguageWithContext(language: String, context: Context) {
        val locale = when (language) {
            "Русский" -> Locale("ru", "RU") // Russian
            "Беларуская" -> Locale("be", "BY") // Belarusian ✅ (исправлено)
            "English" -> Locale("en") // English
            "Deutsch" -> Locale("de") // German
            "Français" -> Locale("fr") // French
            "Polski" -> Locale("pl") // Polish
            "Español" -> Locale("es") // Spanish
            "Eesti" -> Locale("et") // Estonian
            "Italiano" -> Locale("it") // Italian
            "Ελληνικά" -> Locale("el") // Greek
            "हिन्दी" -> Locale("hi") // Hindi
            "فارسی" -> Locale("fa") // Persian
            "العربية" -> Locale("ar") // Arabic (Standard)
            "العربية الفلسطينية" -> Locale("ar", "PS") // Palestinian Arabic
            "Bahasa Indonesia" -> Locale("id") // Indonesian
            "עברית" -> Locale("he") // Hebrew
            "ייִדיש" -> Locale("yi") // Yiddish
            "Татарча" -> Locale("tt") // Tatar
            "Башҡортса" -> Locale("ba") // Bashkir
            "Қазақша" -> Locale("kk") // Kazakh
            "Кыргызча" -> Locale("ky", "KG") // Kyrgyz ✅ (исправлено)
            "Тоҷикӣ" -> Locale("tg") // Tajik
            "Oʻzbekcha" -> Locale("uz") // Uzbek
            "Հայերեն" -> Locale("hy") // Armenian
            "Azərbaycan dili" -> Locale("az") // Azerbaijani
            else -> Locale("ru", "RU")
        }

        // Устанавливаем глобальный локаль
        Locale.setDefault(locale)

        // Обновляем конфигурацию ресурсов (ВАЖНО!)
        val resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)

        // Применяем изменения
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    /*private fun updateAppLanguage(language: String, activity: Activity) {
        val locale = when (language) {
            "Русский" -> Locale("ru", "RU")
            "Беларуская" -> Locale("be", "BY")
            "English" -> Locale("en")
            "Deutsch" -> Locale("de")
            "Français" -> Locale("fr")
            "Polski" -> Locale("pl")
            "Español" -> Locale("es")
            "Eesti" -> Locale("et")
            "Italiano" -> Locale("it")
            "Ελληνικά" -> Locale("el")
            "हिन्दी" -> Locale("hi")
            "فارسی" -> Locale("fa")
            "العربية" -> Locale("ar")
            "العربية الفلسطينية" -> Locale("ar", "PS")
            "Bahasa Indonesia" -> Locale("id")
            "עברית" -> Locale("he")
            "ייִדיש" -> Locale("yi")
            "Татарча" -> Locale("tt")
            "Башҡортса" -> Locale("ba")
            "Қазақша" -> Locale("kk")
            "Кыргызча" -> Locale("ky", "KG")
            "Тоҷикӣ" -> Locale("tg")
            "Oʻzbekcha" -> Locale("uz")
            "Հայերեն" -> Locale("hy")
            "Azərbaycan dili" -> Locale("az")
            else -> Locale("ru", "RU")
        }

        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)

        val context = activity.createConfigurationContext(config)
        activity.resources.updateConfiguration(config, activity.resources.displayMetrics)
        activity.baseContext.resources.updateConfiguration(config, activity.baseContext.resources.displayMetrics)
    }*/



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NoteApp() {
        val homeViewModel: HomeViewModel = viewModel()
        val bookmarkViewModel: BookmarkViewModel = viewModel()
        val aboutScreenViewModel: AboutScreenViewModel = viewModel()
        val navController = rememberNavController()
        var currentTab by remember { mutableStateOf(TabScreen.Home) }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            InputChip(
                                selected = currentTab == TabScreen.Home,
                                onClick = {
                                    currentTab = TabScreen.Home
                                    navController.navigateToSingleTop(route = Screens.Home.name)
                                },
                                label = { Text(text = "Заметки") },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                }
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            InputChip(
                                selected = currentTab == TabScreen.BookMark,
                                onClick = {
                                    currentTab = TabScreen.BookMark
                                    navController.navigateToSingleTop(route = Screens.Bookmark.name)
                                },
                                label = { Text(text = "Важное") },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Default.Bookmark, contentDescription = null)
                                }
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            InputChip(
                                selected = currentTab == TabScreen.AppAbout,
                                onClick = {
                                    currentTab = TabScreen.AppAbout
                                    navController.navigateToSingleTop(route = Screens.AboutScreen.name)
                                },
                                label = { Text("i") },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                                },
                                modifier = Modifier.width(IntrinsicSize.Min)
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            currentTab = TabScreen.Home
                            navController.navigateToSingleTop(route = "${Screens.Detail}")
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            NoteNavigation(
                modifier = Modifier.padding(it),
                navHostController = navController,
                homeViewModel = homeViewModel,
                bookmarkViewModel = bookmarkViewModel,
                aboutScreenViewModel = aboutScreenViewModel,
                assistedFactory = assistedFactory
            )
        }
    }
}


