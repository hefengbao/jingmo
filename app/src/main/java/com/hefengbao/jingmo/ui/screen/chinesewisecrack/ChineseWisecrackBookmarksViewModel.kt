package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.ChineseWisecrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseWisecrackBookmarksViewModel @Inject constructor(
    private val repository: ChineseWisecrackRepository
) : ViewModel() {
    val bookmarks = repository.collections()

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            repository.uncollect(id)
        }
    }
}