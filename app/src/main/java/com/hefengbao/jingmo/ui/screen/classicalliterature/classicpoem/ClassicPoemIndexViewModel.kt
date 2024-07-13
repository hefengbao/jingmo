/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicPoemIndexViewModel @Inject constructor(
    private val repository: ClassicPoemRepository
) : ViewModel() {
    init {
        getRandom()
    }

    private val _classicPoemEntity: MutableStateFlow<ClassicPoemEntity?> = MutableStateFlow(null)
    val classicPoemEntity: SharedFlow<ClassicPoemEntity?> = _classicPoemEntity

    fun getRandom() {
        viewModelScope.launch {
            repository.random().collectLatest { _classicPoemEntity.value = it }
        }
    }

    private val _classicPoemCollectionEntity: MutableStateFlow<ClassicPoemCollectionEntity?> =
        MutableStateFlow(null)
    val classicPoemCollectionEntity: SharedFlow<ClassicPoemCollectionEntity?> =
        _classicPoemCollectionEntity

    fun isCollected(id: Int) {
        viewModelScope.launch {
            repository.isCollect(id).collectLatest {
                _classicPoemCollectionEntity.value = it
            }
        }
    }

    fun collect(id: Int) {
        viewModelScope.launch {
            repository.collect(ClassicPoemCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}