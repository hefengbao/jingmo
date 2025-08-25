/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpressionIndexViewModel @Inject constructor(
    private val repository: ExpressionRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    init {
        getRandom()
    }

    private val _expressionEntity: MutableStateFlow<ExpressionEntity?> = MutableStateFlow(null)
    val expressionEntity: SharedFlow<ExpressionEntity?> = _expressionEntity

    fun getRandom() {
        viewModelScope.launch {
            repository.getRandom().collectLatest {
                _expressionEntity.value = it
            }
        }
    }
}