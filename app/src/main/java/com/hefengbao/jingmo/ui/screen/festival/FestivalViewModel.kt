package com.hefengbao.jingmo.ui.screen.festival

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.Festival
import com.hefengbao.jingmo.data.repository.FestivalRepository
import com.hefengbao.jingmo.ui.screen.festival.nav.FestivalShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FestivalRepository
) : ViewModel() {
    private val args = FestivalShowArgs(savedStateHandle)

    private val _festival: MutableStateFlow<Festival?> = MutableStateFlow(null)
    val festival: SharedFlow<Festival?> = _festival

    init {
        viewModelScope.launch {
            _festival.value = repository.getList().first {
                it.id == args.festivalId.toInt()
            }
        }
    }
}