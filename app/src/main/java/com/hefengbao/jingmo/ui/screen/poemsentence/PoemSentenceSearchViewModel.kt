package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PoemSentenceSearchViewModel @Inject constructor(
    private val repository: PoemSentenceRepository
) : ViewModel() {
    private val query = MutableStateFlow("")
    fun search(query: String) {
        this.query.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val sentences = query.debounce(200)
        .distinctUntilChanged()
        .filter { return@filter it.isNotEmpty() }
        .flatMapLatest { repository.searchSentencesList(it) }
        .cachedIn(viewModelScope)
}