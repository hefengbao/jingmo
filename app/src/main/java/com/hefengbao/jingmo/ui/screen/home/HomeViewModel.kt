package com.hefengbao.jingmo.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.HomeItem
import com.hefengbao.jingmo.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository
) : ViewModel() {
    private val _homeItem: MutableStateFlow<HomeItem> = MutableStateFlow(HomeItem())
    val homeItem: SharedFlow<HomeItem> = _homeItem

    init {
        viewModelScope.launch {
            homeRepository.getHomeItem().collectLatest { _homeItem.value = it }
        }
    }
}