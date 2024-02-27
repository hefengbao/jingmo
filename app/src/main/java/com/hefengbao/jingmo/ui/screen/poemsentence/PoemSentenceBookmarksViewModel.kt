package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemSentenceBookmarksViewModel @Inject constructor(
    private val repository: PoemSentenceRepository
) : ViewModel() {
    val bookmarks = repository.collections()

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}