package com.hefengbao.jingmo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class CaptureViewModel @Inject constructor(
    private val colorRepository: ColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _appStatus: MutableStateFlow<AppStatus?> = MutableStateFlow(null)
    val appStatus: SharedFlow<AppStatus?> = _appStatus

    private val _colors: MutableStateFlow<List<ChineseColor>> = MutableStateFlow(emptyList())
    val colors: SharedFlow<List<ChineseColor>> = _colors

    init {
        getColors()

        viewModelScope.launch {
            preferenceRepository.getAppStatus().collectLatest {
                _appStatus.value = it
            }
        }
    }

    fun getColors() {
        viewModelScope.launch {
            _colors.value = colorRepository.list()
        }
    }

    fun setCaptureTextColor(color: String) {
        viewModelScope.launch {
            preferenceRepository.setCaptureTextColor(color)
        }
    }

    fun setCaptureBackgroundColor(color: String) {
        viewModelScope.launch {
            preferenceRepository.setCaptureBackgroundColor(color)
        }
    }
}