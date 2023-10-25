package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.model.DataStatus
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.PoemSentenceCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSentenceCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val poemSentenceRepository: PoemSentenceRepository,
    private val chineseColorRepository: ChineseColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: PoemSentenceCaptureArgs = PoemSentenceCaptureArgs(savedStateHandle)

    private val _poemSentence: MutableStateFlow<PoemSentenceEntity?> = MutableStateFlow(null)
    val poemSentence: SharedFlow<PoemSentenceEntity?> = _poemSentence

    lateinit var dataStatus: DataStatus

    init {
        viewModelScope.launch {
            dataStatus = preferenceRepository.getDataStatus().first()
            _poemSentence.value =
                poemSentenceRepository.getSentence(args.poemSentenceId.toLong()).first()
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
            preferenceRepository.setCaptureColor(color)
        }
    }

    fun setCaptureBackgroundColor(color: String) {
        viewModelScope.launch {
            preferenceRepository.setCaptureBackgroundColor(color)
        }
    }
}