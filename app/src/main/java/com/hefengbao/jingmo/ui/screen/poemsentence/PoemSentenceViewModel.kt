package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSentenceViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val poemSentenceRepository: PoemSentenceRepository
) : ViewModel() {
    var id = 1

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id = it.poemSentencesLastReadId
            }
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setPoemSentencesLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = poemSentenceRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = poemSentenceRepository.getPrevId(id)
        }
    }

    private val _sentence: MutableStateFlow<PoemSentenceEntity?> = MutableStateFlow(null)
    val sentence: SharedFlow<PoemSentenceEntity?> = _sentence
    fun getSentence(id: Int) {
        viewModelScope.launch {
            poemSentenceRepository.getSentence(id).collectLatest {
                _sentence.value = it
            }
        }
    }
}