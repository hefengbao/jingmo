package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hefengbao.jingmo.data.repository.IdiomRepository
import com.hefengbao.jingmo.ui.screen.idiom.nav.IdiomArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdiomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    idiomRepository: IdiomRepository
) : ViewModel() {

    private val idiomArgs = IdiomArgs(savedStateHandle)

    val idiom = idiomRepository.getIdiom(idiomArgs.idiomId.toInt())
}