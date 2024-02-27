package com.hefengbao.jingmo.ui.screen.tonguetwister

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.repository.TongueTwisterRepository
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.TongueTwisterShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TongueTwisterShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val tongueTwisterRepository: TongueTwisterRepository
) : ViewModel() {
    private val args = TongueTwisterShowArgs(savedStateHandle)

    val id = args.tongueTwisterId.toInt()

    private val _tongueTwister: MutableStateFlow<TongueTwisterEntity?> = MutableStateFlow(null)
    val tongueTwister: SharedFlow<TongueTwisterEntity?> = _tongueTwister

    init {
        viewModelScope.launch {
            tongueTwisterRepository.getTongueTwister(id).collectLatest { tongueTwister ->
                _tongueTwister.value = tongueTwister
            }
        }
    }
}
