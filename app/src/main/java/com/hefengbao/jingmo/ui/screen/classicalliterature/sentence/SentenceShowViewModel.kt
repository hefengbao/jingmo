package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepository
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.ClassicalLiteratureSentenceCaptureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceShowViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository,
    private val sentenceRepository: SentenceRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(bookmarkRepository) {
    private val args: ClassicalLiteratureSentenceCaptureArgs =
        ClassicalLiteratureSentenceCaptureArgs(savedStateHandle)

    private val _sentenceEntity: MutableStateFlow<SentenceEntity?> = MutableStateFlow(null)
    val sentenceEntity: SharedFlow<SentenceEntity?> = _sentenceEntity

    init {

        viewModelScope.launch {
            sentenceRepository.get(args.id.toInt()).collectLatest {
                _sentenceEntity.value = it
            }
        }
    }
}