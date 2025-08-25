/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.QuoteShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class QuoteShowViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
    bookmarkRepository: BookmarkRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(bookmarkRepository) {
    private val args = QuoteShowArgs(savedStateHandle)
    private var id = MutableStateFlow(args.id.toInt())

    val quoteEntity = id.flatMapLatest { quoteRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
}