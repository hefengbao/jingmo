/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteIndexViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    private val _quoteEntity: MutableStateFlow<QuoteEntity?> = MutableStateFlow(null)
    val quoteEntity: SharedFlow<QuoteEntity?> = _quoteEntity

    init {
        getRandom()
    }

    fun getRandom() {
        viewModelScope.launch {
            quoteRepository.getRandom().collectLatest {
                _quoteEntity.value = it
            }
        }
    }
}