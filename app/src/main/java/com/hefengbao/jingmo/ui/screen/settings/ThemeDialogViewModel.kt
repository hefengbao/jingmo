package com.hefengbao.jingmo.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.theme.DarkThemeConfig
import com.hefengbao.jingmo.data.model.theme.ThemeBrand
import com.hefengbao.jingmo.data.repository.settings.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ThemeDialogViewModel @Inject constructor(
    private val repository: ThemeRepository
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        repository.appStatus.map { status ->
            SettingsUiState.Success(
                settings = UserEditableSettings(
                    brand = status.themeBrand,
                    darkThemeConfig = status.darkThemeConfig,
                    useDynamicColor = status.useDynamicColor
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5.seconds.inWholeMilliseconds),
            initialValue = SettingsUiState.Loading,
        )

    fun updateThemeBrand(themeBrand: ThemeBrand) {
        viewModelScope.launch {
            repository.setThemeBrand(themeBrand)
        }
    }

    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            repository.setDarkThemeConfig(darkThemeConfig)
        }
    }

    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch {
            repository.setDynamicColorPreference(useDynamicColor)
        }
    }
}

data class UserEditableSettings(
    val brand: ThemeBrand,
    val useDynamicColor: Boolean,
    val darkThemeConfig: DarkThemeConfig,
)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserEditableSettings) : SettingsUiState
}