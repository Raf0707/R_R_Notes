package raf.console.rrnotes.presentation.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import raf.console.rrnotes.AppViewModel
import raf.console.rrnotes.presentation.about_app.AboutScreen
import raf.console.rrnotes.presentation.about_app.AboutScreenViewModel
import raf.console.rrnotes.presentation.bookmark.BookmarkScreen
import raf.console.rrnotes.presentation.bookmark.BookmarkViewModel
import raf.console.rrnotes.presentation.detail.DetailAssistedFactory
import raf.console.rrnotes.presentation.detail.DetailScreen
import raf.console.rrnotes.presentation.home.HomeScreen
import raf.console.rrnotes.presentation.home.HomeViewModel
import raf.console.rrnotes.presentation.settings.SettingsScreen
import raf.console.rrnotes.presentation.welcome.WelcomeScreen
import raf.console.utils.ThemeOption

enum class Screens {
    Home, Detail, Bookmark, AboutScreen, SettingsScreen
}

@Composable
fun NoteNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    bookmarkViewModel: BookmarkViewModel,
    aboutScreenViewModel: AboutScreenViewModel,
    assistedFactory: DetailAssistedFactory,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.name
    ) {
        composable(route = Screens.Home.name) {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onBookmarkChange = homeViewModel::onBookMarkChange,
                onDeleteNote = homeViewModel::deleteNote,
                onNoteClicked = {
                    navHostController.navigateToSingleTop(
                        route = "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }
        composable(route = Screens.Bookmark.name) {
            val state by bookmarkViewModel.state.collectAsState()
            BookmarkScreen(
                state = state,
                modifier = modifier,
                onBookMarkChange = bookmarkViewModel::onBookmarkChange,
                onDelete = bookmarkViewModel::deleteNote,
                onNoteClicked = {
                    navHostController.navigateToSingleTop(
                        route = "${Screens.Detail.name}?id=$it"
                    )
                }
            )
        }
        composable(
            route = "${Screens.Detail.name}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1L
            DetailScreen(
                noteId = id,
                assistedFactory = assistedFactory,
                navigateUp = { navHostController.navigateUp() }
            )
        }
        composable(route = Screens.AboutScreen.name) {
            // Получаем состояние, если оно нужно
            val state by aboutScreenViewModel.state.collectAsState()

            // Отображаем экран
            AboutScreen(navHostController)
        }



        composable(route = Screens.SettingsScreen.name) {
            val viewModel: AppViewModel = hiltViewModel()
            val selectedTheme by viewModel.theme.collectAsState()
            val isDynamicColorsEnabled by viewModel.dynamicColor.collectAsState()
            val contrastThemeEnabled by viewModel.contrastTheme.collectAsState()

            SettingsScreen(
                isDynamicColorsEnabled = isDynamicColorsEnabled,
                onDynamicColorsToggle = { viewModel.setDynamicColor(it) },
                selectedTheme = selectedTheme,
                onThemeChange = { viewModel.setTheme(it) },
                contrastThemeEnabled = contrastThemeEnabled,
                onContrastThemeToggle = { viewModel.setContrastTheme(it) }
            )
        }
    }

}

fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}









