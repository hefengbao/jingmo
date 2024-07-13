/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.festival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.traditionalculture.Festival
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalIndexViewModel @Inject constructor(
    private val repository: FestivalRepository
) : ViewModel() {
    private val _festivals: MutableStateFlow<List<Festival>> = MutableStateFlow(emptyList())
    val festivals: SharedFlow<List<Festival>> = _festivals

    fun getList() {
        viewModelScope.launch {
            _festivals.value = repository.list()
        }
    }
}