package com.hefengbao.jingmo.ui.screen.chinese.character

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterIndexViewModel @Inject constructor(
    private val repository: CharacterRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {
    init {
        getRandom()
    }

    private val _characterEntity: MutableStateFlow<CharacterEntity?> = MutableStateFlow(null)
    val characterEntity: SharedFlow<CharacterEntity?> = _characterEntity

    fun getRandom() {
        viewModelScope.launch {
            repository.getRandom().collectLatest { _characterEntity.value = it }
        }
    }
}