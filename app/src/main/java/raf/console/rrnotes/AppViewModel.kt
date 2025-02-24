package raf.console.rrnotes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import raf.console.rrnotes.utils.ThemeOption
import java.util.Locale

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application)

    val theme: StateFlow<ThemeOption> = dataStoreManager.getTheme()
        .map { ThemeOption.fromThemeString(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeOption.SystemDefault
        )

    val dynamicColor: StateFlow<Boolean> = dataStoreManager.getDynamicColor()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val contrastTheme: StateFlow<Boolean> = dataStoreManager.getContrastTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val selectedLanguage: StateFlow<Locale> = dataStoreManager.getLanguage()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Locale("ru") // Default language
        )

    val visitCount: StateFlow<Int> = dataStoreManager.getVisitCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val requestSent: StateFlow<Boolean> = dataStoreManager.getRequestSent()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    // Новый StateFlow для языка
    val language: StateFlow<Locale> = dataStoreManager.getLanguage()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Locale("ru") // Значение по умолчанию
        )

    // Функции для обновления данных

    fun setTheme(theme: ThemeOption) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setTheme(theme.toThemeString())
        }
    }

    fun setDynamicColor(dynamicColor: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setDynamicColor(dynamicColor)
        }
    }

    fun setContrastTheme(contrast: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setContrastTheme(contrast)
        }
    }

    fun setVisitCount(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setVisitCount(count)
        }
    }

    fun setRequestSent(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setRequestSent(status)
        }
    }

    // Функция для обновления языка
    /*fun setLanguage(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setLanguage(language)
        }
    }*/

    fun setLanguage(locale: Locale) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setLanguage(locale)
        }

    }

    fun setLanguage(locale: Locale, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setLanguage(locale)
        }
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }



    class AppViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                return AppViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
