package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassicPoemBookmarksViewModel @Inject constructor(
    repository: ClassicPoemRepository
) : ViewModel() {
    val poems = repository.collections().cachedIn(viewModelScope)
}