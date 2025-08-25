/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val repository: BookmarkRepository
) : ViewModel() {
    @OptIn(ExperimentalTime::class)
    fun addBookmark(id: Int, model: String) {
        viewModelScope.launch {
            val localDateTime = LocalDateTime.now()
            val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")
            repository.add(
                BookmarkEntity(
                    id = dateTimeFormatter.format(localDateTime).toLong(),
                    bookmarkableId = id,
                    bookmarkableModel = model
                )
            )
        }
    }

    fun cancelBookmark(id: Int, model: String) {
        viewModelScope.launch {
            repository.cancel(id, model)
        }
    }

    private val _bookmarkEntity: MutableStateFlow<BookmarkEntity?> = MutableStateFlow(null)
    val bookmarkEntity: SharedFlow<BookmarkEntity?> = _bookmarkEntity
    fun isBookmarked(id: Int, model: String) {
        viewModelScope.launch {
            repository.bookmarked(id, model).collectLatest { _bookmarkEntity.value = it }
        }
    }
}