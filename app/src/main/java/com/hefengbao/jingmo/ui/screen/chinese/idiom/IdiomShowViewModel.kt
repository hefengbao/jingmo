/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.IdiomShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val idiomRepository: IdiomRepository
) : ViewModel() {

    private val idiomShowArgs = IdiomShowArgs(savedStateHandle)

    val idiom = idiomRepository.getIdiom(idiomShowArgs.idiomId.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val idiomCollectionEntity = idiomRepository.isCollect(idiomShowArgs.idiomId.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.collect(IdiomCollectionEntity(id))
        }
    }
}