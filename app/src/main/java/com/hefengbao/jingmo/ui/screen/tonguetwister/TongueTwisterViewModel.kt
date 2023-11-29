package com.hefengbao.jingmo.ui.screen.tonguetwister

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.TongueTwisterRepository
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.TongueTwisterArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TongueTwisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val preferenceRepository: PreferenceRepository,
    private val tongueTwisterRepository: TongueTwisterRepository
) : ViewModel() {
    private val args = TongueTwisterArgs(savedStateHandle)

    val id = args.tongueTwisterId.toInt()

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setTongueTwisterLastReadId(id)
        }
    }

    private val _nextId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val nextId: SharedFlow<Int?> = _nextId
    fun getNextId(id: Int) {
        viewModelScope.launch {
            _nextId.value = tongueTwisterRepository.getNextId(id)
        }
    }

    private val _prevId: MutableStateFlow<Int?> = MutableStateFlow(null)
    val prevId: SharedFlow<Int?> = _prevId
    fun getPrevId(id: Int) {
        viewModelScope.launch {
            _prevId.value = tongueTwisterRepository.getPrevId(id)
        }
    }

    private val _tongueTwister: MutableStateFlow<TongueTwisterEntity?> = MutableStateFlow(null)
    val tongueTwister: SharedFlow<TongueTwisterEntity?> = _tongueTwister
    fun getTongueTwister(id: Int) {
        viewModelScope.launch {
            tongueTwisterRepository.getTongueTwister(id).collectLatest { tongueTwister ->
                _tongueTwister.value = tongueTwister
            }
        }
    }
}
