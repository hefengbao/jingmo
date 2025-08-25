/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicPoemIndexViewModel @Inject constructor(
    private val classicPoemRepository: ClassicPoemRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    init {
        getRandom()
    }

    private val _classicPoemEntity: MutableStateFlow<ClassicPoemEntity?> = MutableStateFlow(null)
    val classicPoemEntity: SharedFlow<ClassicPoemEntity?> = _classicPoemEntity

    fun getRandom() {
        viewModelScope.launch {
            classicPoemRepository.getRandom().collectLatest { _classicPoemEntity.value = it }
        }
    }
}