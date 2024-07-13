/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.traditionalculture.festival

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.traditionalculture.Festival
import com.hefengbao.jingmo.data.repository.traditionalculture.FestivalRepository
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav.FestivalShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FestivalRepository
) : ViewModel() {
    private val args = FestivalShowArgs(savedStateHandle)

    private val _festival: MutableStateFlow<Festival?> = MutableStateFlow(null)
    val festival: SharedFlow<Festival?> = _festival

    init {
        viewModelScope.launch {
            _festival.value = repository.list().first {
                it.id == args.festivalId.toInt()
            }
        }
    }
}