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
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnowledgeIndexViewModel @Inject constructor(
    private val repository: KnowledgeRepository
) : ViewModel() {
    init {
        random()
    }

    private val _knowledgeEntity: MutableStateFlow<KnowledgeEntity?> = MutableStateFlow(null)
    val knowledgeEntity: SharedFlow<KnowledgeEntity?> = _knowledgeEntity

    fun random() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _knowledgeEntity.value = it
            }
        }
    }

    private val _knowledgeCollectionEntity: MutableStateFlow<KnowledgeCollectionEntity?> =
        MutableStateFlow(null)
    val knowledgeCollectionEntity: SharedFlow<KnowledgeCollectionEntity?> =
        _knowledgeCollectionEntity

    fun isCollect(id: Int) {
        viewModelScope.launch {
            repository.isCollect(id).collectLatest {
                _knowledgeCollectionEntity.value = it
            }
        }
    }

    fun collect(id: Int) {
        viewModelScope.launch {
            repository.collect(KnowledgeCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}