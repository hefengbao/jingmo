package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.ui.screen.idiom.nav.IdiomArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
    private val idiomRepository: IdiomRepository
) : ViewModel() {

    private val idiomArgs = IdiomArgs(savedStateHandle)

    val id = idiomArgs.idiomId.toInt()

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setIdiomsLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = idiomRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = idiomRepository.getPrevId(id)
        }
    }

    private val _idiom: MutableStateFlow<IdiomEntity?> = MutableStateFlow(null)
    val idiom: SharedFlow<IdiomEntity?> = _idiom
    fun getIdiom(id: Int) {
        viewModelScope.launch {
            _idiom.value = idiomRepository.getIdiom(id)
        }
    }
}