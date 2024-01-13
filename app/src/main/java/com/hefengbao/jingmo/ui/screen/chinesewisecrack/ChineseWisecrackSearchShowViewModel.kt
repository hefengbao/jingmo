package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.ChineseWisecrackSearchShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseWisecrackSearchShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chineseWisecrackRepository: ChineseWisecrackRepository
) : ViewModel() {

    private val args = ChineseWisecrackSearchShowArgs(savedStateHandle)

    val id = args.id.toInt()
    val query = args.query

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int, query: String) {
        viewModelScope.launch {
            _nextId.value = chineseWisecrackRepository.getSearchNextId(id, query)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int, query: String) {
        viewModelScope.launch {
            _prevId.value = chineseWisecrackRepository.getSearchPrevId(id, query)
        }
    }

    private val _chineseCrack: MutableStateFlow<ChineseWisecrackEntity?> = MutableStateFlow(null)
    val chineseCrack: SharedFlow<ChineseWisecrackEntity?> = _chineseCrack
    fun getChineseWisecrack(id: Int) {
        viewModelScope.launch {
            _chineseCrack.value = chineseWisecrackRepository.getChineseCrack(id)
        }
    }
}