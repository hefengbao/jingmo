/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModernPoetryIndexViewModel @Inject constructor(
    private val repository: ModernPoetryRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {
    init {
        random()
    }

    private val _poetryEntity: MutableStateFlow<ModernPoetryEntity?> = MutableStateFlow(null)
    val poetryEntity: SharedFlow<ModernPoetryEntity?> = _poetryEntity

    fun random() {
        viewModelScope.launch {
            repository.getRandom().collectLatest {
                _poetryEntity.value = it
            }
        }
    }
}