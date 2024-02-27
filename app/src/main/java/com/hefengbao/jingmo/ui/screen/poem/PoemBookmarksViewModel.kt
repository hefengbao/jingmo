package com.hefengbao.jingmo.ui.screen.poem

import androidx.lifecycle.ViewModel
import com.hefengbao.jingmo.data.repository.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PoemBookmarksViewModel @Inject constructor(
    writingRepository: WritingRepository
) : ViewModel() {
    val poems = writingRepository.collections()
}