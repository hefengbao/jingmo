package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.repository.PoemRepository
import com.hefengbao.jingmo.ui.screen.poem.nav.PoemSearchShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSearchShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val poemRepository: PoemRepository
) : ViewModel() {
    private val poemArgs = PoemSearchShowArgs(savedStateHandle)

    val id = poemArgs.poemId.toLong()
    val query = poemArgs.queryString

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long, query: String) {
        viewModelScope.launch {
            _nextId.value = poemRepository.getSearchNextId(id, query)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long, query: String) {
        viewModelScope.launch {
            _prevId.value = poemRepository.getSearchPrevId(id, query)
        }
    }

    private val _poem: MutableStateFlow<PoemWithWriterAndTags?> = MutableStateFlow(null)
    val poem: SharedFlow<PoemWithWriterAndTags?> = _poem
    fun getPoem(id: Long) {
        viewModelScope.launch {
            _poem.value = poemRepository.getPoemWithWriterAndTags(id)
        }
    }
}