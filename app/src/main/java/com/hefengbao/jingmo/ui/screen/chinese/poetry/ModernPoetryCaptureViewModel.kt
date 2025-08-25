package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.CaptureViewModel
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.ModernPoetryCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ModernPoetryCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    poetryRepository: ModernPoetryRepository,
    colorRepository: ColorRepository,
    preferenceRepository: PreferenceRepository
) : CaptureViewModel(colorRepository, preferenceRepository) {
    private val args = ModernPoetryCaptureArgs(savedStateHandle)

    val poetryEntity = poetryRepository.get(args.id.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}