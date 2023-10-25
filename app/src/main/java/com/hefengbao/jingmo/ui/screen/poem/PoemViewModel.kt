package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.repository.PoemRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.ui.screen.poem.nav.PoemArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
    private val poemRepository: PoemRepository
) : ViewModel() {
    private val poemArgs: PoemArgs = PoemArgs(savedStateHandle)

    val id = poemArgs.poemId.toLong()

    fun setLastReadId(id: Long) {
        viewModelScope.launch {
            preferenceRepository.setPoemLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long) {
        viewModelScope.launch {
            _nextId.value = poemRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long) {
        viewModelScope.launch {
            _prevId.value = poemRepository.getPrevId(id)
        }
    }

    private val _poem: MutableStateFlow<PoemWithWriterAndTags?> = MutableStateFlow(null)
    val poem: SharedFlow<PoemWithWriterAndTags?> = _poem
    fun getPoem(id: Long) {
        viewModelScope.launch {
            _poem.value = poemRepository.getPoemWithWriterAndTags(id)
        }
    }
}