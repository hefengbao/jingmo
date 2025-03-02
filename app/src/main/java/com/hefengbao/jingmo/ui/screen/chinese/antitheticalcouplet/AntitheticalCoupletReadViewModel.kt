/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletCollectionEntity
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
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
class AntitheticalCoupletReadViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val repository: AntitheticalCoupletRepository
) : ViewModel() {

    private var id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.chineseAntitheticalCoupletLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
        setLastReadId(id)
    }

    val antitheticalCouplet = id.flatMapLatest {
        repository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val antitheticalCoupletCollection = id.flatMapLatest {
        repository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        repository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        repository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            repository.collect(AntitheticalCoupletCollectionEntity(id))
        }
    }

    private fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setChineseAntitheticalCoupletLastReadId(id)
        }
    }
}