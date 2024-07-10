/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.data.repository.ChineseExpressionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpressionIndexViewModel @Inject constructor(
    private val repository: ChineseExpressionRepository
) : ViewModel() {

    init {
        getRandom()
    }

    private val _expression: MutableStateFlow<ChineseExpressionEntity?> = MutableStateFlow(null)
    val expression: SharedFlow<ChineseExpressionEntity?> = _expression

    fun getRandom() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _expression.value = it
            }
        }
    }
}