package com.hefengbao.jingmo.ui.screen.chinese.lyric

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.CaptureViewModel
import com.hefengbao.jingmo.data.repository.chinese.LyricRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.LyricCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LyricCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    lyricRepository: LyricRepository,
    colorRepository: ColorRepository,
    preferenceRepository: PreferenceRepository
) : CaptureViewModel(colorRepository, preferenceRepository) {
    private val args = LyricCaptureArgs(savedStateHandle)

    val lyricEntity = lyricRepository.get(args.id.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}