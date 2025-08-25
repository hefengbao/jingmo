/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.color

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.data.repository.traditionalculture.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorIndexViewModel @Inject constructor(
    private val repository: ColorRepository
) : ViewModel() {

    private val _colors: MutableStateFlow<List<ChineseColor>> =
        MutableStateFlow(emptyList())
    val colors: SharedFlow<List<ChineseColor>> = _colors
    fun getList() {
        viewModelScope.launch {
            _colors.value = repository.list()
        }
    }

    private val _searchColors: MutableStateFlow<List<ChineseColor>> =
        MutableStateFlow(emptyList())
    val searchColors: SharedFlow<List<ChineseColor>> = _searchColors
    fun search(query: String) {
        _searchColors.value = emptyList()
        viewModelScope.launch {
            _searchColors.value = repository.list().filter {
                it.name.contains(query)
            }
        }
    }
}