package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseWisecrackSearchViewModel @Inject constructor(
    private val repository: ChineseWisecrackRepository
) : ViewModel() {
    private val _searchWisecrackList: MutableStateFlow<List<ChineseWisecrackEntity>> =
        MutableStateFlow(emptyList())
    val searchWisecrackList: SharedFlow<List<ChineseWisecrackEntity>> = _searchWisecrackList

    fun search(query: String) {
        _searchWisecrackList.value = emptyList()
        viewModelScope.launch {
            repository.searchWisecrackList(query).collectLatest {
                _searchWisecrackList.value = it
            }
        }
    }
}