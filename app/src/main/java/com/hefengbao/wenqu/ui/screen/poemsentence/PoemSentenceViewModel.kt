package com.hefengbao.wenqu.ui.screen.poem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.wenqu.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.wenqu.data.repository.PoemRepository
import com.hefengbao.wenqu.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val poemRepository: PoemRepository
): ViewModel() {
    var id = 1L
    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().poemLastReadId
        }
    }

    fun setLastReadId(id: Long){
        viewModelScope.launch {
            preferenceRepository.setPoemLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long){
        viewModelScope.launch {
            _nextId.value = poemRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long){
        viewModelScope.launch {
            _prevId.value = poemRepository.getPrevId(id)
            Log.i("PoemVM", _prevId.value.toString())
        }
    }

    private val _poem: MutableStateFlow<PoemWithWriterAndTags?> = MutableStateFlow(null)
    val poem: SharedFlow<PoemWithWriterAndTags?> = _poem
    fun getPoem(id: Long){
        viewModelScope.launch {
            _poem.value = poemRepository.getPoem(id)
        }
    }
}