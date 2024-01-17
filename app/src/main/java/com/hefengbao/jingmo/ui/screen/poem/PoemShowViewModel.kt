package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PoemShowViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val writingRepository: WritingRepository,
) : ViewModel() {

    private var id = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.writingsLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val writing = id.flatMapLatest {
        writingRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        writingRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        writingRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = null
    )

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            writingRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            writingRepository.collect(WritingCollectionEntity(id))
        }
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setWritingsLastReadId(id)
        }
    }
}