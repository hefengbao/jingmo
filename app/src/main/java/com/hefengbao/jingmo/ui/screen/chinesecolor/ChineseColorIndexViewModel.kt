package com.hefengbao.jingmo.ui.screen.chinesecolor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseColorIndexViewModel @Inject constructor(
    private val repository: ChineseColorRepository
) : ViewModel() {

    private val _chineseColors: MutableStateFlow<List<ChineseColor>> =
        MutableStateFlow(emptyList<ChineseColor>())
    val chineseColors: SharedFlow<List<ChineseColor>> = _chineseColors
    fun getList() {
        viewModelScope.launch {
            _chineseColors.value = repository.getList()
        }
    }

    private val _searchChineseColors: MutableStateFlow<List<ChineseColor>> =
        MutableStateFlow(emptyList<ChineseColor>())
    val searchChineseColors: SharedFlow<List<ChineseColor>> = _searchChineseColors
    fun search(query: String) {
        _searchChineseColors.value = emptyList()
        viewModelScope.launch {
            _searchChineseColors.value = repository.getList().filter {
                it.name.contains(query)
            }
        }
    }
}