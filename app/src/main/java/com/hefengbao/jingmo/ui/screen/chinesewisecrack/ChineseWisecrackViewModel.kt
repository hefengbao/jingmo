package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseWisecrackViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val chineseWisecrackRepository: ChineseWisecrackRepository
) : ViewModel() {
    var id = 1

    init {
        viewModelScope.launch {
            id = preferenceRepository.getReadStatus().first().chineseWisecracksLastReadId
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setChineseWisecracksLastReadId(id.toInt())
        }
    }

    var query = ""

    fun onQueryChange(query: String) {
        this.query = query
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = chineseWisecrackRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = chineseWisecrackRepository.getPrevId(id)
        }
    }

    private val _chineseCrack: MutableStateFlow<ChineseWisecrackEntity?> = MutableStateFlow(null)
    val chineseCrack: SharedFlow<ChineseWisecrackEntity?> = _chineseCrack
    fun getChineseWisecrack(id: Int) {
        viewModelScope.launch {
            _chineseCrack.value = chineseWisecrackRepository.getChineseCrack(id)
        }
    }

    private val _searchWisecrackList: MutableStateFlow<List<ChineseWisecrackEntity>> =
        MutableStateFlow(
            emptyList()
        )
    val searchWisecrackList: SharedFlow<List<ChineseWisecrackEntity>> = _searchWisecrackList

    fun search(query: String) {
        _searchWisecrackList.value = emptyList()
        viewModelScope.launch {
            chineseWisecrackRepository.searchWisecrackList(query).collectLatest {
                _searchWisecrackList.value = it
            }
        }
    }
}