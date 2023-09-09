package com.hefengbao.wenqu.ui.screen.poemsentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.wenqu.data.database.entity.SentenceWithPoem
import com.hefengbao.wenqu.data.repository.PoemSentenceRepository
import com.hefengbao.wenqu.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSentenceViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val poemSentenceRepository: PoemSentenceRepository
) : ViewModel() {
    var id = 1L

    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().poemSentenceLastReadId
        }
    }

    fun setLastReadId(id: Long) {
        viewModelScope.launch {
            preferenceRepository.setPoemSentenceLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long) {
        viewModelScope.launch {
            _nextId.value = poemSentenceRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long) {
        viewModelScope.launch {
            _prevId.value = poemSentenceRepository.getPrevId(id)
        }
    }

    private val _sentence: MutableStateFlow<SentenceWithPoem?> = MutableStateFlow(null)
    val sentence: SharedFlow<SentenceWithPoem?> = _sentence
    fun getSentence(id: Long) {
        viewModelScope.launch {
            _sentence.value = poemSentenceRepository.getSentence(id)
        }
    }
}