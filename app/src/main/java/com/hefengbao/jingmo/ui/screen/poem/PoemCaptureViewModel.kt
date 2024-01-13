package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.PoemRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.ui.screen.poem.nav.PoemCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val poemRepository: PoemRepository,
    private val chineseColorRepository: ChineseColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: PoemCaptureArgs = PoemCaptureArgs(savedStateHandle)

    private val _poem: MutableStateFlow<PoemEntity?> = MutableStateFlow(null)
    val poem: SharedFlow<PoemEntity?> = _poem

    lateinit var appStatus: AppStatus

    init {
        viewModelScope.launch {
            appStatus = preferenceRepository.getAppStatus().first()
            _poem.value = poemRepository.getPoem(args.poemId.toLong()).first()
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