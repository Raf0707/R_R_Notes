package raf.console.rrnotes.presentation.about_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import raf.console.rrnotes.R
import raf.console.rrnotes.presentation.navigation.Screens
import raf.console.rrnotes.presentation.settings.SettingsScreen
import raf.console.rrnotes.utils.ChromeCustomTabUtil
import raf.console.rrnotes.utils.FeedbackHelper


@Composable
fun AboutScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 80.dp)

    ) {
        item {
            ElevatedCard(shape = ShapeDefaults.Large) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    
                    val imageRes = R.drawable.arch_notes_logo_dark_theme
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "App Icon",
                        modifier = Modifier.size(128.dp)
                    )
                    Text(text = stringResource(R.string.app_name))
                    //Text(text = "R&R")
                    ListItem(
                        title = stringResource(R.string.bug_report_title),
                        subtitle = stringResource(R.string.bug_report_subtitle),
                        icon = painterResource(id = R.drawable.bug_report_24px),
                        onClick = {
                            FeedbackHelper.SendEmail(context)
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.rate_the_app_title),
                        subtitle = stringResource(R.string.rate_the_app_subtitle),
                        icon = painterResource(id = R.drawable.rate_review_24px),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://www.rustore.ru/catalog/app/raf.console.rrnotes",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.other_apps_title),
                        subtitle = stringResource(R.string.other_apps_subtitle),
                        icon = painterResource(id = R.drawable.apps_24px),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://apps.rustore.ru/developer/ZPBnoCoBczpBFPZK0munW8NSpRTEayCj",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.settings),
                        subtitle = stringResource(R.string.open_settings),
                        icon = painterResource(id = R.drawable.settings_24px),
                        onClick = {

                            navController.navigate(Screens.SettingsScreen.name)
                        }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ElevatedCard(shape = ShapeDefaults.Large) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    /*ListItem(
                        title = stringResource(R.string.website_title),
                        subtitle = stringResource(R.string.website_subtitle),
                        icon = painterResource(id = R.drawable.web_24px),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://byteflipper.web.app",
                            )
                        }
                    )*/
                    //HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.vk_title),
                        subtitle = stringResource(R.string.vk_subtitle),
                        icon = painterResource(id = R.drawable.vk_24),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://vk.com/mahabbaa",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.telegram_title),
                        subtitle = stringResource(R.string.telegram_subtitle),
                        icon = painterResource(id = R.drawable.telegram_24),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://t.me/ibnRustum",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.github_title),
                        subtitle = stringResource(R.string.github_subtitle),
                        icon = painterResource(id = R.drawable.github_24),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://github.com/Raf0707",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.source_code),
                        subtitle = stringResource(R.string.open_source_code_github),
                        icon = painterResource(id = R.drawable.code_24px),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://github.com/Raf0707/R_R_Notes",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
                    ListItem(
                        title = stringResource(R.string.version),
                        subtitle = "1.0.1",
                        icon = painterResource(id = R.drawable.update_24px)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ElevatedCard(shape = ShapeDefaults.Large) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    val imageRes = R.drawable.book_open_dark_theme
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Book Icon",
                        modifier = Modifier.size(128.dp)
                    )
                    Text(
                        text = stringResource(R.string.do_you_like_reading),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.read_your_favourite_books),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.rafbook_reader),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )
                    ListItem(
                        title = stringResource(R.string.dounload_rafbook_reader),
                        subtitle = stringResource(R.string.download_from_rustore),
                        icon = painterResource(id = R.drawable.book_open),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://www.rustore.ru/catalog/app/raf.console.chitalka",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))

                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ElevatedCard(shape = ShapeDefaults.Large) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    val imageRes = R.drawable.code_24px_dark_theme

                    Image(
                        painter = painterResource(imageRes),
                        contentDescription = "Code Icon",
                        modifier = Modifier.size(128.dp)
                    )
                    Text(
                        text = stringResource(R.string.raf_console_studio),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.are_your_programmer),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.study_programming),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.would_you_like_talking),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp, start = 32.dp, end = 32.dp),
                        textAlign = TextAlign.Center
                    )
                    ListItem(
                        title = stringResource(R.string.go_to_raf_console),
                        subtitle = stringResource(R.string.tg_raf_console_studio),
                        icon = painterResource(id = R.drawable.code_24px),
                        onClick = {
                            ChromeCustomTabUtil.openUrl(
                                context = context,
                                url = "https://t.me/rafConsoleStudio",
                            )
                        }
                    )
                    HorizontalDivider(Modifier.padding(16.dp, 0.dp, 16.dp, 32.dp))

                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListItem(
    title: String = stringResource(R.string.title_list_item),
    subtitle: String = stringResource(R.string.subtitle_list_item),
    icon: Painter = painterResource(id = R.drawable.ic_launcher_foreground),
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
