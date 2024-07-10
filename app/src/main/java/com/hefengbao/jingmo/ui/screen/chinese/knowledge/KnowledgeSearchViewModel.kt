/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.repository.ChineseKnowledgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnowledgeSearchViewModel @Inject constructor(
    private val chineseKnowledgeRepository: ChineseKnowledgeRepository
) : ViewModel() {
    private val _chineseKnowledgeList: MutableStateFlow<List<ChineseKnowledgeEntity>> =
        MutableStateFlow(
            emptyList()
        )

    val chineseKnowledgeList: SharedFlow<List<ChineseKnowledgeEntity>> = _chineseKnowledgeList

    fun search(query: String) {
        viewModelScope.launch {
            chineseKnowledgeRepository.getSearchChineseKnowledgeList(query).collectLatest {
                _chineseKnowledgeList.value = it
            }
        }
    }
}