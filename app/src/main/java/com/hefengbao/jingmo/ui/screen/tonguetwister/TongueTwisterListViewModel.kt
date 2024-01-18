package com.hefengbao.jingmo.ui.screen.tonguetwister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import com.hefengbao.jingmo.data.repository.TongueTwisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TongueTwisterListViewModel @Inject constructor(
    repository: TongueTwisterRepository,
) : ViewModel() {

    val tongueTwisters: SharedFlow<List<SimpleTongueTwister>> =
        repository.getTongueTwisterList().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}