package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.PoemSentenceSearchShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSentenceSearchShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val poemSentenceRepository: PoemSentenceRepository
) : ViewModel() {
    private val args = PoemSentenceSearchShowArgs(savedStateHandle)

    val id = args.id.toLong()
    val query = args.query

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long, query: String) {
        viewModelScope.launch {
            _nextId.value = poemSentenceRepository.getSearchNextId(id, query)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long, query: String) {
        viewModelScope.launch {
            _prevId.value = poemSentenceRepository.getSearchPrevId(id, query)
        }
    }

    private val _sentence: MutableStateFlow<SentenceWithPoem?> = MutableStateFlow(null)
    val sentence: SharedFlow<SentenceWithPoem?> = _sentence
    fun getSentence(id: Long) {
        viewModelScope.launch {
            _sentence.value = poemSentenceRepository.getSentenceWithPoem(id)
        }
    }
}