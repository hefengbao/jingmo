/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.HomeItem
import com.hefengbao.jingmo.data.repository.settings.HomeItemRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeItemRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _homeItem: MutableStateFlow<HomeItem> = MutableStateFlow(HomeItem())
    val homeItem: SharedFlow<HomeItem> = _homeItem

    private val _showSyncDataTip: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showSyncDataTip: SharedFlow<Boolean> = _showSyncDataTip

    init {
        viewModelScope.launch {
            homeRepository.getHomeItem().collectLatest { _homeItem.value = it }
        }
        viewModelScope.launch {
            preferenceRepository.getAppStatus().collectLatest {
                _showSyncDataTip.value = it.showSyncDataTip
            }
        }
    }

    fun updateShowSyncDataTip() {
        viewModelScope.launch {
            preferenceRepository.setShowSyncDataTip(false)
        }
    }
}