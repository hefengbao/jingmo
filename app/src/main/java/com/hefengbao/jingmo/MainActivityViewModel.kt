/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.UserData
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    init {
        // 只是为了把 preference 数据加载到内存
        viewModelScope.launch {
            preferenceRepository.getAppStatus().collectLatest { }
            preferenceRepository.getDatasetStatus().collectLatest { }
            preferenceRepository.getReadStatus().collectLatest { }
        }
    }

    val uiState: StateFlow<MainActivityUiState> = preferenceRepository.getAppStatus().map {
        MainActivityUiState.Success(
            userData = UserData(
                themeBrand = it.themeBrand,
                darkThemeConfig = it.darkThemeConfig,
                useDynamicColor = it.useDynamicColor,
                showUserAgreementTip = it.showUserAgreementTip
            )
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val showLanding: SharedFlow<Boolean> = _showLanding
    fun closeLanding() {
        viewModelScope.launch {
            delay(1500)
            _showLanding.value = false
        }
    }

    fun updateUserAgreementVersion() {
        viewModelScope.launch {
            preferenceRepository.setUserAgreementVersion(BuildConfig.USER_AGREEMENT_VERSION)
        }
    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}