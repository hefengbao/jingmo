/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisecrackIndexViewModel @Inject constructor(
    private val wisecrackRepository: WisecrackRepository
) : ViewModel() {

    init {
        getRandom()
    }

    private val _wiseCrack: MutableStateFlow<WisecrackEntity?> = MutableStateFlow(null)
    val wiseCrack: SharedFlow<WisecrackEntity?> = _wiseCrack

    fun getRandom() {
        viewModelScope.launch {
            wisecrackRepository.random().collectLatest { _wiseCrack.value = it }
        }
    }

    private val _wiseCrackCollection: MutableStateFlow<WisecrackCollectionEntity?> =
        MutableStateFlow(null)
    val wiseCrackCollection: SharedFlow<WisecrackCollectionEntity?> = _wiseCrackCollection
    fun isCollect(id: Int) {
        viewModelScope.launch {
            wisecrackRepository.isCollect(id).collectLatest {
                _wiseCrackCollection.value = it

                Log.i("WisecrackIndexViewModel", it.toString())
            }
        }
    }

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            wisecrackRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            wisecrackRepository.collect(WisecrackCollectionEntity(id))
        }
    }
}