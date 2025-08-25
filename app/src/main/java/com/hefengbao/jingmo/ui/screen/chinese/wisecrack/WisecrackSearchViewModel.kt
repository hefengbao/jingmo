/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisecrackSearchViewModel @Inject constructor(
    private val repository: WisecrackRepository
) : ViewModel() {
    private val _wisecrackEntities: MutableStateFlow<List<WisecrackEntity>> =
        MutableStateFlow(emptyList())
    val wisecrackEntities: SharedFlow<List<WisecrackEntity>> = _wisecrackEntities

    fun search(query: String) {
        _wisecrackEntities.value = emptyList()
        viewModelScope.launch {
            repository.search(query).collectLatest {
                _wisecrackEntities.value = it
            }
        }
    }
}