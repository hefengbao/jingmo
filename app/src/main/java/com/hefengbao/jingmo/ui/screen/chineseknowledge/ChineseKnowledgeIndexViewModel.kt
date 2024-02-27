package com.hefengbao.jingmo.ui.screen.chineseknowledge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.ChineseKnowledgeRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseKnowledgeIndexViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val chineseKnowledgeRepository: ChineseKnowledgeRepository
) : ViewModel() {
    var id = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            id.value = preferenceRepository.getReadStatus().first().chineseKnowledgeLastReadId
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val chineseKnowledge = id.flatMapLatest {
        chineseKnowledgeRepository.getChineseKnowledge(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        chineseKnowledgeRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        chineseKnowledgeRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    /*private val _chineseKnowledge: MutableStateFlow<ChineseKnowledgeEntity?> =
        MutableStateFlow(null)
    val chineseKnowledge: SharedFlow<ChineseKnowledgeEntity?> = _chineseKnowledge

    fun getChineseKnowledge(id: Int) {
        viewModelScope.launch {
            chineseKnowledgeRepository.getChineseKnowledge(id).collectLatest {
                _chineseKnowledge.value = it
            }
        }
    }

    private val _prevId: MutableStateFlow<Int> = MutableStateFlow(0)
    val prevId: SharedFlow<Int> = _prevId

    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = chineseKnowledgeRepository.getPrevId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int> = MutableStateFlow(0)
    val nextId: SharedFlow<Int> = _nextId

    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = chineseKnowledgeRepository.getNextId(id)
        }
    }*/

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setChineseKnowledgeLastReadId(id)
        }
    }
}