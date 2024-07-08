/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.repository.PeopleRepository
import com.hefengbao.jingmo.data.repository.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class WritingSearchViewModel @Inject constructor(
    private val writingRepository: WritingRepository,
    peopleRepository: PeopleRepository,
) : ViewModel() {

    private val _recommendList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val recommendList: SharedFlow<List<String>> = _recommendList

    init {
        _recommendList.value = peopleRepository.getRecommendList()
    }

    private val queryStateFlow = MutableStateFlow("")
    var type = "keyword"
    fun search(query: String, type: String) {
        queryStateFlow.value = query
        this.type = type
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val writings: Flow<PagingData<SimpleWritingInfo>> = queryStateFlow.debounce(200)
        .distinctUntilChanged()
        .filter {
            return@filter it.isNotEmpty()
        }.flatMapLatest { query ->
            if (type == "keyword") {
                writingRepository.search(query)
            } else {
                writingRepository.searchByAuthor(query)
            }
        }.cachedIn(viewModelScope)
}