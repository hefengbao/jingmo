package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.ui.screen.idiom.nav.IdiomShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdiomShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val idiomRepository: IdiomRepository
) : ViewModel() {

    private val idiomShowArgs = IdiomShowArgs(savedStateHandle)

    val idiom = idiomRepository.getIdiom(idiomShowArgs.idiomId.toInt())

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            idiomRepository.collect(IdiomCollectionEntity(id))
        }
    }
}