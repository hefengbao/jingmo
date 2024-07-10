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
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
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
    private val poemSentenceRepository: PoemSentenceRepository,
    private val chineseColorRepository: ChineseColorRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val args: PoemSentenceCaptureArgs = PoemSentenceCaptureArgs(savedStateHandle)

    private val _poemSentence: MutableStateFlow<PoemSentenceEntity?> = MutableStateFlow(null)
    val poemSentence: SharedFlow<PoemSentenceEntity?> = _poemSentence

    lateinit var appStatus: AppStatus

    init {
        viewModelScope.launch {
            appStatus = preferenceRepository.getAppStatus().first()

            poemSentenceRepository.getSentence(args.poemSentenceId.toInt()).collectLatest {
                _poemSentence.value = it
            }
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