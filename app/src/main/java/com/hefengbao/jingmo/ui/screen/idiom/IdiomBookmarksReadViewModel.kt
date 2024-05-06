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
import kotlinx.coroutines.flow.SharingStarted
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
    var collectedAt = MutableStateFlow(0L)

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    fun setCurrentCollectedAt(collectedAt: Long) {
        this.collectedAt.value = collectedAt
    }

    val idiom = id.flatMapLatest {
        repository.getIdiom(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val idiomCollectionEntity = id.flatMapLatest {
        repository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = collectedAt.flatMapLatest {
        repository.getCollectionPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = collectedAt.flatMapLatest {
        repository.getCollectionNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

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