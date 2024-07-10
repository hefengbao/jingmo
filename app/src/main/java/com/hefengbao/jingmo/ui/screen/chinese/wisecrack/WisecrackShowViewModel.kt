/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.ChineseWisecrackSearchShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WisecrackShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chineseWisecrackRepository: ChineseWisecrackRepository
) : ViewModel() {

    private val args = ChineseWisecrackSearchShowArgs(savedStateHandle)

    var id = MutableStateFlow(args.id.toInt())

    val wisecrack = id.flatMapLatest {
        chineseWisecrackRepository.getChineseCrack(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val chineseWisecrackCollectionEntity = id.flatMapLatest {
        chineseWisecrackRepository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            chineseWisecrackRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            chineseWisecrackRepository.collect(ChineseWisecrackCollectionEntity(id))
        }
    }
}