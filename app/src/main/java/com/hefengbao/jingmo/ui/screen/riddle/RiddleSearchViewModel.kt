package com.hefengbao.jingmo.ui.screen.riddle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.repository.RiddleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiddleSearchViewModel @Inject constructor(
    private val riddleRepository: RiddleRepository
) : ViewModel() {

    private val _list: MutableStateFlow<List<RiddleEntity>> = MutableStateFlow(emptyList())
    val list: SharedFlow<List<RiddleEntity>> = _list
    fun search(query: String) {
        viewModelScope.launch {
            riddleRepository.searchResultList(query).collectLatest {
                _list.value = it
            }
        }
    }
}