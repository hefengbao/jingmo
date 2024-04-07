package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.repository.WritingRepository
import com.hefengbao.jingmo.ui.screen.writing.nav.WritingSearchArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class WritingSearchViewModel @Inject constructor(
    private val writingRepository: WritingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = WritingSearchArgs(savedStateHandle)
    val type = args.type
    val query = args.query

    private val queryStateFlow = MutableStateFlow("")
    fun search(query: String) {
        queryStateFlow.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val writings: Flow<PagingData<SimpleWritingInfo>> = if (type == "author") {
        writingRepository.searchByAuthor(query).cachedIn(viewModelScope)
    } else {
        queryStateFlow.debounce(200)
            .distinctUntilChanged()
            .filter {
                return@filter it.isNotEmpty()
            }.flatMapLatest { query ->
                writingRepository.search(query)
            }.cachedIn(viewModelScope)
    }
}