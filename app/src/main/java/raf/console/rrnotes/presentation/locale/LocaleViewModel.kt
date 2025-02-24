package raf.console.rrnotes.presentation.locale

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class LocaleViewModel : ViewModel() {
    private val _currentLocale = MutableStateFlow(Locale.getDefault())
    val currentLocale: StateFlow<Locale> = _currentLocale.asStateFlow()

    fun updateLocale(locale: Locale) {
        _currentLocale.value = locale
    }
}