package com.hefengbao.jingmo.ui.screen.chinesecolor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.data.repository.ChineseColorRepository
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.ChineseColorArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseColorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: ChineseColorRepository
) : ViewModel() {
    private val args = ChineseColorArgs(savedStateHandle)

    private val _chineseColor: MutableStateFlow<ChineseColor?> = MutableStateFlow(null)
    val chineseColor: SharedFlow<ChineseColor?> = _chineseColor

    init {
        viewModelScope.launch {
            _chineseColor.value = repository.getList().first {
                it.id == args.chineseColorId
            }
        }
    }
}