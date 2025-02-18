package raf.console.rrnotes.presentation.about_app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor() : ViewModel() {
    // Тут можно хранить состояние, если оно понадобится в будущем.
    private val _state: MutableStateFlow<AboutScreenState> = MutableStateFlow(AboutScreenState())
    val state: StateFlow<AboutScreenState> = _state.asStateFlow()

    init {
        // Инициализация данных для AboutScreen, если нужно.
    }
}

data class AboutScreenState(
    val message: String = "Welcome to About Screen"
)
