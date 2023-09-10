package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val idiomRepository: IdiomRepository
) : ViewModel() {
    var id = 1L

    init {
        viewModelScope.launch {
            id = preferenceRepository.getDataStatus().first().idiomLastReadId
        }
    }

    fun setLastReadId(id: Long) {
        viewModelScope.launch {
            preferenceRepository.setIdiomLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val nextId: SharedFlow<Long?> = _nextId
    fun getNextId(id: Long) {
        viewModelScope.launch {
            _nextId.value = idiomRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Long?> = MutableStateFlow(null)
    val prevId: SharedFlow<Long?> = _prevId
    fun getPrevId(id: Long) {
        viewModelScope.launch {
            _prevId.value = idiomRepository.getPrevId(id)
        }
    }

    private val _idiom: MutableStateFlow<IdiomEntity?> = MutableStateFlow(null)
    val idiom: SharedFlow<IdiomEntity?> = _idiom
    fun getIdiom(id: Long) {
        viewModelScope.launch {
            _idiom.value = idiomRepository.getIdiom(id)
        }
    }
}