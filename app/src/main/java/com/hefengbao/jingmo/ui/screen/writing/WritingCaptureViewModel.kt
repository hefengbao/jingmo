package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.WritingRepository
import com.hefengbao.jingmo.ui.screen.writing.nav.WritingCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val writingRepository: WritingRepository,
    private val chineseColorRepository: ChineseColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: WritingCaptureArgs = WritingCaptureArgs(savedStateHandle)

    private val _writing: MutableStateFlow<WritingWithBookmark?> = MutableStateFlow(null)
    val writing: SharedFlow<WritingWithBookmark?> = _writing

    lateinit var appStatus: AppStatus

    init {
        viewModelScope.launch {
            appStatus = preferenceRepository.getAppStatus().first()
            _writing.value = writingRepository.get(args.poemId.toInt()).first()
        }
    }

    private val _chineseColors: MutableStateFlow<List<ChineseColor>> = MutableStateFlow(emptyList())
    val chineseColors: SharedFlow<List<ChineseColor>> = _chineseColors
    fun getColors() {
        viewModelScope.launch {
            _chineseColors.value = chineseColorRepository.getList()
        }
    }

    fun setCaptureColor(color: String) {
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