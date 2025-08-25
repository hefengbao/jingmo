/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.proverb

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.datastore.ReadStatusPreference
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepository
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
class ProverbReadViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository,
    private val repository: ProverbRepository,
    private val preference: ReadStatusPreference,
) : BaseViewModel(bookmarkRepository) {
    private val id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preference.readStatus.collectLatest {
                id.value = it.chineseProverbLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id

        viewModelScope.launch { preference.setChineseProverbLastReadId(id) }
    }

    val proverbEntity = id.flatMapLatest { repository.get(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    val prevId = id.flatMapLatest { repository.prevId(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )

    val nextId = id.flatMapLatest { repository.nextId(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed((5_000)),
        initialValue = null
    )
}