/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.color

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav.ChineseColorArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: ChineseColorRepository
) : ViewModel() {
    private val args = ChineseColorArgs(savedStateHandle)

    private val _chineseColor: MutableStateFlow<ChineseColor?> = MutableStateFlow(null)
    val chineseColor: SharedFlow<ChineseColor?> = _chineseColor

    init {
        viewModelScope.launch {
            _chineseColor.value = repository.getList().first {
                it.id == args.chineseColorId
            }
        }
    }
}