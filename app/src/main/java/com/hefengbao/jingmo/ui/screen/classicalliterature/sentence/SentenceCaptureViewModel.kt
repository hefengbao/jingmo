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
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.CaptureViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
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
    colorRepository: ColorRepository,
    preferenceRepository: PreferenceRepository
) : CaptureViewModel(colorRepository, preferenceRepository) {
    private val args: ClassicalLiteratureSentenceCaptureArgs =
        ClassicalLiteratureSentenceCaptureArgs(savedStateHandle)

    private val _sentenceEntity: MutableStateFlow<SentenceEntity?> = MutableStateFlow(null)
    val sentenceEntity: SharedFlow<SentenceEntity?> = _sentenceEntity

    init {

        viewModelScope.launch {
            sentenceRepository.get(args.id.toInt()).collectLatest {
                _sentenceEntity.value = it
            }
        }
    }
}