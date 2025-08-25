/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class ClassicPoemReadViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val classicPoemRepository: ClassicPoemRepository,
    bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {
    private var id = MutableStateFlow(1)
    private var query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            preferenceRepository.getReadStatus().collectLatest {
                id.value = it.classicLiteratureClassicPoemLastReadId
            }
        }
    }

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun setLastReadId(id: Int) {
        viewModelScope.launch {
            preferenceRepository.setClassicalLiteratureClassicPoemLastReadId(id)
        }
    }

    val classicPoemEntity = id.flatMapLatest {
        classicPoemRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = id.flatMapLatest {
        classicPoemRepository.getPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = id.flatMapLatest {
        classicPoemRepository.getNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val idTitles = query.debounce(500).flatMapLatest {
        classicPoemRepository.getIdTitle(it)
    }.cachedIn(viewModelScope)
}