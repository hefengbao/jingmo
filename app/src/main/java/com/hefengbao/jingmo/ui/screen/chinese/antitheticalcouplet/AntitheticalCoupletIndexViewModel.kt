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
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AntitheticalCoupletIndexViewModel @Inject constructor(
    private val repository: AntitheticalCoupletRepository,
) : ViewModel() {
    init {
        getRandom()
    }

    private val _antitheticalCouplet: MutableStateFlow<AntitheticalCoupletEntity?> =
        MutableStateFlow(null)
    val antitheticalCouplet: SharedFlow<AntitheticalCoupletEntity?> = _antitheticalCouplet
    fun getRandom() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _antitheticalCouplet.value = it
            }
        }
    }

    private val _antitheticalCoupletCollection: MutableStateFlow<AntitheticalCoupletCollectionEntity?> =
        MutableStateFlow(null)
    val antitheticalCoupletCollection: SharedFlow<AntitheticalCoupletCollectionEntity?> =
        _antitheticalCoupletCollection

    fun getIdiomCollectionEntity(id: Int) {
        viewModelScope.launch {
            repository.isCollect(id).collectLatest {
                _antitheticalCoupletCollection.value = it
            }
        }
    }

    fun collect(id: Int) {
        viewModelScope.launch {
            repository.collect(AntitheticalCoupletCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}