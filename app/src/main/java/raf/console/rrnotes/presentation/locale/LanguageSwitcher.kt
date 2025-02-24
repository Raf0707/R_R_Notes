package raf.console.rrnotes.presentation.locale

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale
/*
@JvmOverloads
@Composable
fun LanguageSwitcher(
    viewModel: LocaleViewModel = viewModel()
) {
    val supportedLocales = remember {
        listOf(
            "Русский" to Locale("ru"),
            "Белорусский" to Locale("be"),
            "Английский" to Locale("en"),
            "Немецкий" to Locale("de"),
            "Французский" to Locale("fr"),
            "Польский" to Locale("pl"),
            "Испанский" to Locale("es"),
            "Эстонский" to Locale("et"),
            "Итальянский" to Locale("it"),
            "Греческий" to Locale("el"),
            "Хинди" to Locale("hi"),
            "Персидский" to Locale("fa"),
            "Арабский" to Locale("ar"),
            "Арабский Палестинский" to Locale("ar", "PS"),
            "Индонезийский" to Locale("id"),
            "Иврит" to Locale("he"),
            "Идиш" to Locale("yi"),
            "Татарский" to Locale("tt"),
            "Башкирский" to Locale("ba"),
            "Казахский" to Locale("kk"),
            "Кыргызский" to Locale("ky"),
            "Таджикский" to Locale("tg"),
            "Узбекский" to Locale("uz"),
            "Армянский" to Locale("hy"),
            "Азербайджанский" to Locale("az")
        )
    }

    Column(
        modifier = Modifier.run { fillMaxSize().padding(16.dp) }
    ) {
        items(supportedLocales) { (languageName, locale) ->
            Button(
                onClick = { viewModel.updateLocale(locale) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(languageName)
            }
        }
    }
}*/