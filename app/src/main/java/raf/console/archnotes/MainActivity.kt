package raf.console.archnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import raf.console.archnotes.presentation.about_app.AboutScreenViewModel
import raf.console.archnotes.presentation.bookmark.BookmarkViewModel
import raf.console.archnotes.presentation.detail.DetailAssistedFactory
import raf.console.archnotes.presentation.home.HomeViewModel
import raf.console.archnotes.presentation.navigation.NoteNavigation
import raf.console.archnotes.presentation.navigation.Screens
import raf.console.archnotes.presentation.navigation.navigateToSingleTop
import raf.console.archnotes.ui.theme.NoteApplicationTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var assistedFactory: DetailAssistedFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    NoteApp()
                }
            }
        }
    }

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
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            InputChip(
                                selected = currentTab == TabScreen.Home,
                                onClick = {
                                    currentTab = TabScreen.Home
                                    navController.navigateToSingleTop(route = Screens.Home.name)
                                },
                                label = { Text(text = "Заметки") },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null
                                    )
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
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null
                                    )
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
                                    Icon(
                                        imageVector = Icons.Outlined.Info,
                                        contentDescription = null
                                    )
                                },
                                modifier = Modifier.width(IntrinsicSize.Min) // Auto size based on content
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            // При клике меняем currentTab на Home
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

enum class TabScreen {
    Home, BookMark, AppAbout
}
