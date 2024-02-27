package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PoemSentenceIndexViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val poemSentenceRepository: PoemSentenceRepository
) : ViewModel() {
    private var id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.poemSentencesLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setPoemSentencesLastReadId(id)
        }
    }

    val sentence = id.flatMapLatest {
        poemSentenceRepository.getSentence(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        poemSentenceRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        poemSentenceRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            poemSentenceRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            poemSentenceRepository.collect(PoemSentenceCollectionEntity(id))
        }
    }
}