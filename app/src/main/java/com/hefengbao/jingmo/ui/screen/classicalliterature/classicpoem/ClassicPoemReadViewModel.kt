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
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
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
class ClassicPoemReadViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val classicPoemRepository: ClassicPoemRepository
) : ViewModel() {
    private var id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.classicPoemsLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setClassicPoemLastReadId(id)
        }
    }

    val classicPoemEntity = id.flatMapLatest {
        classicPoemRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        classicPoemRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        classicPoemRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val classicPoemCollectionEntity = id.flatMapLatest {
        classicPoemRepository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun collect(id: Int) {
        viewModelScope.launch {
            classicPoemRepository.collect(ClassicPoemCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            classicPoemRepository.uncollect(id)
        }
    }
}