package com.hefengbao.jingmo.ui.screen.idiom

import androidx.lifecycle.ViewModel
import com.hefengbao.jingmo.data.repository.IdiomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdiomBookmarksViewModel @Inject constructor(
    repository: IdiomRepository
) : ViewModel() {
    val bookmarks = repository.collections()
}