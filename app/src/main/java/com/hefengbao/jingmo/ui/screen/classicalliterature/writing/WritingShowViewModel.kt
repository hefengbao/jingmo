/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingCollectionEntity
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.WritingShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WritingShowViewModel @Inject constructor(
    private val writingRepository: WritingRepository,
    savedStateHandle: SavedStateHandle,
    val json: Json
) : ViewModel() {
    private val args = WritingShowArgs(savedStateHandle)
    private var id = MutableStateFlow(args.id.toInt())

    val writing = id.flatMapLatest {
        writingRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val writingCollectionEntity = id.flatMapLatest {
        writingRepository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            writingRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            writingRepository.collect(WritingCollectionEntity(id))
        }
    }
}