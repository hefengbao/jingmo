/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.KnowledgeShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class KnowledgeShowViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository,
    private val knowledgeRepository: KnowledgeRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(bookmarkRepository) {
    private val args = KnowledgeShowArgs(savedStateHandle)
    private var id = MutableStateFlow(args.id.toInt())

    val knowledgeEntity = id.flatMapLatest { knowledgeRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
}