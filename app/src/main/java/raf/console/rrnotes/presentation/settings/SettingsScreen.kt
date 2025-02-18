package raf.console.rrnotes.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import raf.console.rrnotes.AppViewModel
import raf.console.rrnotes.R
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import raf.console.utils.ThemeOption

@Composable
fun SettingsScreen(
    isDynamicColorsEnabled: Boolean,
    onDynamicColorsToggle: (Boolean) -> Unit,
    selectedTheme: ThemeOption,
    onThemeChange: (ThemeOption) -> Unit,
    contrastThemeEnabled: Boolean,
    onContrastThemeToggle: (Boolean) -> Unit,
    onBackClick: () -> Unit // Добавил параметр для кнопки назад
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // Верхний блок с кнопкой назад и заголовком
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад"
                )
            }
            Text(
                text = "Настройки",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // General Settings
        Text(
            text = "General Settings",
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 4.dp),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )

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

        // Theme Settings
        Text(
            text = "Theme Settings",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )

        ThemeOptionItem(
            title = "System Default",
            selected = selectedTheme == ThemeOption.SystemDefault,
            onClick = { onThemeChange(ThemeOption.SystemDefault) }
        )
        ThemeOptionItem(
            title = "Light Theme",
            selected = selectedTheme == ThemeOption.Light,
            onClick = { onThemeChange(ThemeOption.Light) }
        )
        ThemeOptionItem(
            title = "Dark Theme",
            selected = selectedTheme == ThemeOption.Dark,
            onClick = { onThemeChange(ThemeOption.Dark) }
        )

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

    SettingsScreen(
        isDynamicColorsEnabled = dynamicColorsEnabled,
        onDynamicColorsToggle = { appViewModel.setDynamicColor(it) },
        selectedTheme = ThemeOption.valueOf(selectedTheme.toString()),
        onThemeChange = { appViewModel.setTheme(it) },
        contrastThemeEnabled = contrastThemeEnabled,
        onContrastThemeToggle = { appViewModel.setContrastTheme(it) },
        onBackClick = { navController.popBackStack() } // Добавил навигацию назад
    )
}