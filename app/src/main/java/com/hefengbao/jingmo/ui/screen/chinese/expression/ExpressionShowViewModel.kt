/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepository
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.ChineseExpressionShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpressionShowViewModel @Inject constructor(
    private val repository: ExpressionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = ChineseExpressionShowArgs(savedStateHandle)
    private val _expression: MutableStateFlow<ExpressionEntity?> = MutableStateFlow(null)
    val expression: SharedFlow<ExpressionEntity?> = _expression

    init {
        viewModelScope.launch {
            repository.get(args.id.toInt()).collectLatest {
                _expression.value = it
            }
        }
    }
}