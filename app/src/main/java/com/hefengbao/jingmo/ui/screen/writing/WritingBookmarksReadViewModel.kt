package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.repository.WritingRepository
import com.hefengbao.jingmo.ui.screen.writing.nav.WritingBookmarksReadArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WritingBookmarksReadViewModel @Inject constructor(
    private val writingRepository: WritingRepository,
    savedStateHandle: SavedStateHandle,
    val json: Json
) : ViewModel() {
    private val args = WritingBookmarksReadArgs(savedStateHandle)

    private var id = MutableStateFlow(args.writingId.toInt())

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val writing = id.flatMapLatest {
        writingRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(collectedAt: Long) {
        viewModelScope.launch {
            writingRepository.getCollectionNextId(collectedAt).collectLatest {
                _nextId.value = it
            }
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(collectedAt: Long) {
        viewModelScope.launch {
            writingRepository.getCollectionPrevId(collectedAt).collectLatest {
                _prevId.value = it
            }
        }
    }

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
}