package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.ChineseWisecrackSearchShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChineseWisecrackSearchShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chineseWisecrackRepository: ChineseWisecrackRepository
) : ViewModel() {

    private val args = ChineseWisecrackSearchShowArgs(savedStateHandle)

    var id = MutableStateFlow(args.id.toInt())
    val query = args.query

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val wisecrack = id.flatMapLatest {
        chineseWisecrackRepository.getChineseCrack(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        chineseWisecrackRepository.getSearchNextId(it, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        chineseWisecrackRepository.getSearchPrevId(it, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            chineseWisecrackRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            chineseWisecrackRepository.collect(ChineseWisecrackCollectionEntity(id))
        }
    }

    /*private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
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

    private val _chineseCrack: MutableStateFlow<ChineseWisecrackWithCollection?> = MutableStateFlow(null)
    val chineseCrack: SharedFlow<ChineseWisecrackWithCollection?> = _chineseCrack
    fun getChineseWisecrack(id: Int) {
        viewModelScope.launch {
            _chineseCrack.value = chineseWisecrackRepository.getChineseCrack(id)
        }
    }*/
}