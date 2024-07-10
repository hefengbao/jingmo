/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomIndexViewModel @Inject constructor(
    private val idiomRepository: IdiomRepository,
) : ViewModel() {
    init {
        getRandomIdiom()
    }

    private val _idiom: MutableStateFlow<IdiomEntity?> = MutableStateFlow(null)
    val idiom: SharedFlow<IdiomEntity?> = _idiom
    fun getRandomIdiom() {
        viewModelScope.launch {
            idiomRepository.random().collectLatest {
                _idiom.value = it
            }
        }
    }

    private val _idiomCollectionEntity: MutableStateFlow<IdiomCollectionEntity?> =
        MutableStateFlow(null)
    val idiomCollectionEntity: SharedFlow<IdiomCollectionEntity?> = _idiomCollectionEntity
    fun getIdiomCollectionEntity(id: Int) {
        viewModelScope.launch {
            idiomRepository.isCollect(id).collectLatest {
                _idiomCollectionEntity.value = it
            }
        }
    }

    fun collect(id: Int) {
        viewModelScope.launch {
            idiomRepository.collect(IdiomCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.uncollect(id)
        }
    }
}