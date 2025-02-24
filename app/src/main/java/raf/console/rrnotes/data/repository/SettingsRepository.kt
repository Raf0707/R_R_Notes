package raf.console.rrnotes.data.repository

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import raf.console.rrnotes.utils.ThemeOption

// Создаем DataStore
private val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class SettingsRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")
        val CONTRAST_THEME = booleanPreferencesKey("contrast_theme")
        val SELECTED_THEME = stringPreferencesKey("selected_theme")
    }

    // Читаем настройки
    val dynamicColors: Flow<Boolean> = dataStore.data.map { it[DYNAMIC_COLORS] ?: true }
    val contrastTheme: Flow<Boolean> = dataStore.data.map { it[CONTRAST_THEME] ?: false }
    val selectedTheme: Flow<String> = dataStore.data.map { it[SELECTED_THEME] ?: ThemeOption.SystemDefault.name }

    // Сохраняем настройки
    suspend fun setDynamicColors(enabled: Boolean) {
        dataStore.edit { it[DYNAMIC_COLORS] = enabled }
    }

    suspend fun setContrastTheme(enabled: Boolean) {
        dataStore.edit { it[CONTRAST_THEME] = enabled }
    }

    suspend fun setSelectedTheme(theme: ThemeOption) {
        dataStore.edit { it[SELECTED_THEME] = theme.name }
    }
}
