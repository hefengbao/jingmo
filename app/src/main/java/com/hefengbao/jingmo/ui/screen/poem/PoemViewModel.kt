package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.WritingRepository
import com.hefengbao.jingmo.ui.screen.poem.nav.PoemArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
    private val writingRepository: WritingRepository,
) : ViewModel() {
    private val poemArgs: PoemArgs = PoemArgs(savedStateHandle)

    var id = poemArgs.poemId.toInt()
    val type = poemArgs.type
    val query = poemArgs.query

    init {
        if (type == "read") {
            viewModelScope.launch {
                id = preferenceRepository.getReadStatus().first().writingsLastReadId
            }
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setWritingsLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = writingRepository.getNextId(id)
        }
    }

    fun getNextId(id: Int, author: String) {
        viewModelScope.launch {
            _nextId.value = writingRepository.getNextId(id, author)
        }
    }

    fun getSearchNextId(id: Int, query: String) {
        viewModelScope.launch {
            _nextId.value = writingRepository.getSearchNextId(id, query)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = writingRepository.getPrevId(id)
        }
    }

    fun getPrevId(id: Int, author: String) {
        viewModelScope.launch {
            _prevId.value = writingRepository.getPrevId(id, author)
        }
    }

    fun getSearchPrevId(id: Int, query: String) {
        viewModelScope.launch {
            _prevId.value = writingRepository.getSearchPrevId(id, query)
        }
    }

    private val _writing: MutableStateFlow<WritingEntity?> = MutableStateFlow(null)
    val writing: SharedFlow<WritingEntity?> = _writing
    fun getWriting(id: Int) {
        viewModelScope.launch {
            writingRepository.get(id).collectLatest {
                _writing.value = it
            }
        }
    }
}