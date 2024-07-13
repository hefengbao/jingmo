/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.Color
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.PoemSentenceCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sentenceRepository: SentenceRepository,
    private val colorRepository: ColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: PoemSentenceCaptureArgs = PoemSentenceCaptureArgs(savedStateHandle)

    private val _poemSentence: MutableStateFlow<SentenceEntity?> = MutableStateFlow(null)
    val poemSentence: SharedFlow<SentenceEntity?> = _poemSentence

    lateinit var appStatus: AppStatus

    init {
        viewModelScope.launch {
            appStatus = preferenceRepository.getAppStatus().first()

            sentenceRepository.get(args.poemSentenceId.toInt()).collectLatest {
                _poemSentence.value = it
            }
        }
    }

    private val _Colors: MutableStateFlow<List<Color>> = MutableStateFlow(emptyList())
    val colors: SharedFlow<List<Color>> = _Colors
    fun getColors() {
        viewModelScope.launch {
            _Colors.value = colorRepository.list()
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