/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.proverb

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProverbIndexViewModel @Inject constructor(
    private val repository: ProverbRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    init {
        getRandom()
    }

    private val _proverbEntity: MutableStateFlow<ProverbEntity?> = MutableStateFlow(null)
    val proverbEntity: SharedFlow<ProverbEntity?> = _proverbEntity

    fun getRandom() {
        viewModelScope.launch {
            repository.getRandom().collectLatest {
                _proverbEntity.value = it
            }
        }
    }
}