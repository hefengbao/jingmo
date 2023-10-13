package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo
import com.hefengbao.jingmo.data.repository.PoemRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemListViewModel @Inject constructor(
    private val repository: PoemRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    var id = 1L

    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().poemLastReadId
        }
    }

    val poems: StateFlow<List<PoemSimpleInfo>> = repository.getPoemSimpleList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private var _searchResult: MutableStateFlow<List<PoemSimpleInfo>> =
        MutableStateFlow(emptyList())
    val searchResult: SharedFlow<List<PoemSimpleInfo>> = _searchResult
    fun search(query: String) {
        _searchResult.value = emptyList()
        viewModelScope.launch {
            repository.searchPoemSimpleList("%$query%").collect {
                _searchResult.value = it
            }
        }
    }
}