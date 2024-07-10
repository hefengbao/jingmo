/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.PoemSentenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SentenceSearchViewModel @Inject constructor(
    private val repository: PoemSentenceRepository
) : ViewModel() {
    private val query = MutableStateFlow("")
    fun search(query: String) {
        this.query.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val sentences = query.debounce(200)
        .distinctUntilChanged()
        .filter { return@filter it.isNotEmpty() }
        .flatMapLatest { repository.searchSentencesList(it) }
        .cachedIn(viewModelScope)
}