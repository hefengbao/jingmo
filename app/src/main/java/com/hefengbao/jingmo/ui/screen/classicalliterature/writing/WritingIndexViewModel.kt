/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class WritingIndexViewModel @Inject constructor(
    val json: Json,
    private val writingRepository: WritingRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {
    private var maxId = 1000000

    init {
        viewModelScope.launch {
            writingRepository.getMaxId().collectLatest {
                maxId = if (it == 0) 1 else it
                getRandom()
            }
        }
    }

    private val _writingEntity: MutableStateFlow<WritingEntity?> = MutableStateFlow(null)
    val writingEntity: SharedFlow<WritingEntity?> = _writingEntity

    fun getRandom() {
        viewModelScope.launch {
            writingRepository.get(
                (1..maxId).random()
            ).collectLatest {
                _writingEntity.value = it
            }
        }
    }
}