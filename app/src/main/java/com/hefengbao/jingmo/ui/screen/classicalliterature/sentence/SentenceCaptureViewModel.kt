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
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.ClassicalLiteratureSentenceCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceCaptureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sentenceRepository: SentenceRepository,
    private val colorRepository: ColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: ClassicalLiteratureSentenceCaptureArgs =
        ClassicalLiteratureSentenceCaptureArgs(savedStateHandle)

    private val _sentence: MutableStateFlow<SentenceEntity?> = MutableStateFlow(null)
    val sentence: SharedFlow<SentenceEntity?> = _sentence

    private val _appStatus: MutableStateFlow<AppStatus?> = MutableStateFlow(null)
    val appStatus: SharedFlow<AppStatus?> = _appStatus

    init {
        viewModelScope.launch {
            preferenceRepository.getAppStatus().collectLatest {
                _appStatus.value = it
            }

            sentenceRepository.get(args.id.toInt()).collectLatest {
                _sentence.value = it
            }
        }
    }

    private val _colors: MutableStateFlow<List<Color>> = MutableStateFlow(emptyList())
    val colors: SharedFlow<List<Color>> = _colors
    fun getColors() {
        viewModelScope.launch {
            _colors.value = colorRepository.list()
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