/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteIndexViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {
    init {
        random()
    }

    private val _entity: MutableStateFlow<QuoteEntity?> = MutableStateFlow(null)
    val entity: SharedFlow<QuoteEntity?> = _entity

    fun random() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _entity.value = it
            }
        }
    }

    private val _collectionEntity: MutableStateFlow<QuoteCollectionEntity?> =
        MutableStateFlow(null)
    val collectionEntity: SharedFlow<QuoteCollectionEntity?> = _collectionEntity

    fun isCollect(id: Int) {
        viewModelScope.launch {
            repository.isCollect(id).collectLatest {
                _collectionEntity.value = it
            }
        }
    }

    fun collect(id: Int) {
        viewModelScope.launch {
            repository.collect(QuoteCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}