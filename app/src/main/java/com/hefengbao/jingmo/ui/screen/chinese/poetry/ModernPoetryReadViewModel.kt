/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryCollectionEntity
import com.hefengbao.jingmo.data.datastore.ReadStatusPreference
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ModernPoetryReadViewModel @Inject constructor(
    private val repository: ModernPoetryRepository,
    private val preference: ReadStatusPreference,
) : ViewModel() {
    private val id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preference.readStatus.collectLatest {
                id.value = it.chineseModernPoetryLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id

        viewModelScope.launch { preference.setChineseModernPoetryLastReadId(id) }
    }

    val entity = id.flatMapLatest { repository.get(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    val collectionEntity = id.flatMapLatest { repository.isCollect(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    val prevId = id.flatMapLatest { repository.prevId(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    val nextId = id.flatMapLatest { repository.nextId(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    fun collect(id: Int) {
        viewModelScope.launch {
            repository.collect(ModernPoetryCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}