/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.tonguetwister

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.repository.chinese.TongueTwisterRepository
import com.hefengbao.jingmo.ui.screen.chinese.tonguetwister.nav.TongueTwisterShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TongueTwisterShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tongueTwisterRepository: TongueTwisterRepository
) : ViewModel() {
    private val args = TongueTwisterShowArgs(savedStateHandle)

    val id = args.tongueTwisterId.toInt()

    private val _tongueTwister: MutableStateFlow<TongueTwisterEntity?> = MutableStateFlow(null)
    val tongueTwister: SharedFlow<TongueTwisterEntity?> = _tongueTwister

    init {
        viewModelScope.launch {
            tongueTwisterRepository.get(id).collectLatest { tongueTwister ->
                _tongueTwister.value = tongueTwister
            }
        }
    }
}
