/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.solarterm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hefengbao.jingmo.data.model.SolarTerm
import com.hefengbao.jingmo.data.repository.SolarTermsRepository
import com.hefengbao.jingmo.ui.screen.solarterm.nav.SolarTermShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class SolarTermShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SolarTermsRepository
) : ViewModel() {
    private val args = SolarTermShowArgs(savedStateHandle)

    private val _solarTerm: MutableStateFlow<SolarTerm?> = MutableStateFlow(null)
    val solarTerm: SharedFlow<SolarTerm?> = _solarTerm

    init {
        _solarTerm.value = repository.getList().first { args.solarTermId.toInt() == it.id }
    }
}