package com.hefengbao.wenqu.ui.screen.chinesecrack

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.wenqu.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.wenqu.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.wenqu.data.repository.ChineseWisecrackRepository
import com.hefengbao.wenqu.data.repository.PoemRepository
import com.hefengbao.wenqu.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseWisecrackViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val chineseWisecrackRepository: ChineseWisecrackRepository
): ViewModel() {
    var id = 1L
    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().chineseWisecrackLastReadId
        }
    }

    fun setLastReadId(id: Long){
        viewModelScope.launch {
            preferenceRepository.setChineseWisecrackLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long){
        viewModelScope.launch {
            _nextId.value = chineseWisecrackRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long){
        viewModelScope.launch {
            _prevId.value = chineseWisecrackRepository.getPrevId(id)
        }
    }

    private val _chineseCrack: MutableStateFlow<ChineseWisecrackEntity?> = MutableStateFlow(null)
    val chineseCrack: SharedFlow<ChineseWisecrackEntity?> = _chineseCrack
    fun getChineseWisecrack(id: Long){
        viewModelScope.launch {
            _chineseCrack.value = chineseWisecrackRepository.getChineseCrack(id)
        }
    }
}