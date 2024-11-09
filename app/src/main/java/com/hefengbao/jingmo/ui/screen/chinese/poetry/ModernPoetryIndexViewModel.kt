/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModernPoetryIndexViewModel @Inject constructor(
    private val repository: ModernPoetryRepository
) : ViewModel() {
    init {
        random()
    }

    private val _entity: MutableStateFlow<ModernPoetryEntity?> = MutableStateFlow(null)
    val entity: SharedFlow<ModernPoetryEntity?> = _entity

    fun random() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _entity.value = it
            }
        }
    }

    private val _collectionEntity: MutableStateFlow<ModernPoetryCollectionEntity?> =
        MutableStateFlow(null)
    val collectionEntity: SharedFlow<ModernPoetryCollectionEntity?> = _collectionEntity

    fun isCollect(id: Int) {
        viewModelScope.launch {
            repository.isCollect(id).collectLatest {
                _collectionEntity.value = it
            }
        }
    }

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