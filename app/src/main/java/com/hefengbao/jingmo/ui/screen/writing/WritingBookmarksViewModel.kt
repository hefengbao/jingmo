package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritingBookmarksViewModel @Inject constructor(
    writingRepository: WritingRepository
) : ViewModel() {
    val writings = writingRepository.collections().cachedIn(viewModelScope)
}