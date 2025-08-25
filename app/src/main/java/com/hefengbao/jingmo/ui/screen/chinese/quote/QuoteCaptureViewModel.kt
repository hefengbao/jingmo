package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.CaptureViewModel
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.QuoteCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QuoteCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    quoteRepository: QuoteRepository,
    colorRepository: ColorRepository,
    preferenceRepository: PreferenceRepository,
) : CaptureViewModel(colorRepository, preferenceRepository) {
    private val args = QuoteCaptureArgs(savedStateHandle)

    val quoteEntity = quoteRepository.get(args.id.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}