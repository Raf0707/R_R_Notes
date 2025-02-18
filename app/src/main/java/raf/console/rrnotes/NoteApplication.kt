package raf.console.rrnotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import raf.console.rrnotes.data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class NoteApplication : Application() {

    lateinit var appViewModel: AppViewModel

    override fun onCreate() {
        super.onCreate()
        appViewModel = AppViewModel(this)

        // Сохранение текущих значений в DataStore при запуске приложения
        CoroutineScope(Dispatchers.IO).launch {
            appViewModel.setTheme(appViewModel.theme.value)
            appViewModel.setDynamicColor(appViewModel.dynamicColor.value)
            appViewModel.setContrastTheme(appViewModel.contrastTheme.value)
            appViewModel.setVisitCount(appViewModel.visitCount.value)
            appViewModel.setRequestSent(appViewModel.requestSent.value)
        }
    }
}
