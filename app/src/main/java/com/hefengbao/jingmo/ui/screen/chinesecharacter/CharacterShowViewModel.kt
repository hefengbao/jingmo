package com.hefengbao.jingmo.ui.screen.chinesecharacter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.ChineseCharacterRepository
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.CharacterShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterShowViewModel @Inject constructor(
    private val repository: ChineseCharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = CharacterShowArgs(savedStateHandle)

    @OptIn(ExperimentalCoroutinesApi::class)
    val character = MutableStateFlow(args.id).flatMapLatest {
        repository.get(it.toInt())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}