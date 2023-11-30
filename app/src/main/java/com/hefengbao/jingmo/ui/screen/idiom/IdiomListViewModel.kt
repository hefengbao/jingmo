package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomListViewModel @Inject constructor(
    private val idiomRepository: IdiomRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    var id = 1L

    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().idiomLastReadId
        }
    }

    val idioms = idiomRepository.getSimpleIdiomInfoList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private var _searchResult: MutableStateFlow<List<SimpleIdiomInfo>> =
        MutableStateFlow(emptyList())
    val searchResult: SharedFlow<List<SimpleIdiomInfo>> = _searchResult
    fun search(query: String) {
        _searchResult.value = emptyList()
        viewModelScope.launch {
            idiomRepository.searchSimpleIdiomInfoList(query).collect {
                _searchResult.value = it
            }
        }
    }
}