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
    var id = 1L

    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().chineseWisecrackLastReadId
        }
    }

    fun setLastReadId(id: Long) {
        viewModelScope.launch {
            preferenceRepository.setChineseWisecrackLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long) {
        viewModelScope.launch {
            _nextId.value = chineseWisecrackRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long) {
        viewModelScope.launch {
            _prevId.value = chineseWisecrackRepository.getPrevId(id)
        }
    }

    private val _chineseCrack: MutableStateFlow<ChineseWisecrackEntity?> = MutableStateFlow(null)
    val chineseCrack: SharedFlow<ChineseWisecrackEntity?> = _chineseCrack
    fun getChineseWisecrack(id: Long) {
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
            chineseWisecrackRepository.searchWisecrackList("%$query%").collectLatest {
                _searchWisecrackList.value = it
            }
        }
    }
}