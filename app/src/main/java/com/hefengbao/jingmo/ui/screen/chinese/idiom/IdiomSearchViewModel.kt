/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class IdiomSearchViewModel @Inject constructor(
    private val idiomRepository: IdiomRepository,
) : ViewModel() {
    private val queryStateFlow = MutableStateFlow("")
    fun search(query: String) {
        queryStateFlow.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    var idioms = queryStateFlow
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest {
            idiomRepository.search(it)
        }
        .cachedIn(viewModelScope)
}