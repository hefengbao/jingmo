package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.data.repository.IdiomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomIndexViewModel @Inject constructor(
    private val idiomRepository: IdiomRepository,
) : ViewModel() {
    init {
        getRandomIdiom()
    }

    private val _idiom: MutableStateFlow<IdiomWithBookmark?> = MutableStateFlow(null)
    val idiom: SharedFlow<IdiomWithBookmark?> = _idiom
    fun getRandomIdiom() {
        viewModelScope.launch {
            idiomRepository.random().collectLatest {
                _idiom.value = it
            }
        }
    }
}