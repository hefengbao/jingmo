package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.ui.screen.idiom.nav.IdiomBookmarksReadArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class IdiomBookmarksReadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: IdiomRepository
) : ViewModel() {

    private val args = IdiomBookmarksReadArgs(savedStateHandle)

    var id = MutableStateFlow(args.idiomId.toInt())

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    val idiom = id.flatMapLatest {
        repository.getIdiom(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(collectedAt: Long) {
        viewModelScope.launch {
            repository.getCollectionNextId(collectedAt).collectLatest {
                _nextId.value = it
            }
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(collectedAt: Long) {
        viewModelScope.launch {
            repository.getCollectionPrevId(collectedAt).collectLatest {
                _prevId.value = it
            }
        }
    }

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            repository.collect(IdiomCollectionEntity(id))
        }
    }
}