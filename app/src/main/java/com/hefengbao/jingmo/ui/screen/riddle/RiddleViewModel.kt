package com.hefengbao.jingmo.ui.screen.riddle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.RiddleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiddleViewModel @Inject constructor(
    private val riddleRepository: RiddleRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    var id = 1

    init {
        viewModelScope.launch {
            preferenceRepository.getDataStatus().collectLatest {
                id = it.riddleLastReadId
            }
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setRiddleLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = riddleRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = riddleRepository.getPrevId(id)
        }
    }

    private val _riddle: MutableStateFlow<RiddleEntity?> = MutableStateFlow(null)
    val riddle: SharedFlow<RiddleEntity?> = _riddle
    fun getRiddle(id: Int) {
        viewModelScope.launch {
            riddleRepository.getRiddle(id).collectLatest {
                _riddle.value = it
            }
        }
    }
}