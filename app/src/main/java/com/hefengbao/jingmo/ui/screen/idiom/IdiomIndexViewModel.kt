package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.IdiomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class IdiomIndexViewModel @Inject constructor(
    private val idiomRepository: IdiomRepository,
) : ViewModel() {
    private val queryStateFlow = MutableStateFlow("")
    fun search(query: String) {
        queryStateFlow.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    var idioms = queryStateFlow
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            idiomRepository.searchSimpleIdiomInfoList(it)
        }
        .cachedIn(viewModelScope)
}