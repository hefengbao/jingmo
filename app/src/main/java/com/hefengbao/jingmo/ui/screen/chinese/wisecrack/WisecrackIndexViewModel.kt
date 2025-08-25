/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisecrackIndexViewModel @Inject constructor(
    private val wisecrackRepository: WisecrackRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    private val _wisecrackEntity: MutableStateFlow<WisecrackEntity?> = MutableStateFlow(null)
    val wisecrackEntity: SharedFlow<WisecrackEntity?> = _wisecrackEntity

    init {
        getRandom()
    }

    fun getRandom() {
        viewModelScope.launch {
            wisecrackRepository.getRandom().collectLatest { _wisecrackEntity.value = it }
        }
    }
}