/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chineseknowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.ChineseKnowledgeRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChineseKnowledgeIndexViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val chineseKnowledgeRepository: ChineseKnowledgeRepository
) : ViewModel() {
    var id = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            id.value = preferenceRepository.getReadStatus().first().chineseKnowledgeLastReadId
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val chineseKnowledge = id.flatMapLatest {
        chineseKnowledgeRepository.getChineseKnowledge(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        chineseKnowledgeRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        chineseKnowledgeRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setChineseKnowledgeLastReadId(id)
        }
    }
}