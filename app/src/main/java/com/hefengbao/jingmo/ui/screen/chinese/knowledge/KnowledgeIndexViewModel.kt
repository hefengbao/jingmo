/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnowledgeIndexViewModel @Inject constructor(
    private val knowledgeRepository: KnowledgeRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel(bookmarkRepository) {

    init {
        getRandom()
    }

    private val _knowledgeEntity: MutableStateFlow<KnowledgeEntity?> = MutableStateFlow(null)
    val knowledgeEntity: SharedFlow<KnowledgeEntity?> = _knowledgeEntity

    fun getRandom() {
        viewModelScope.launch {
            knowledgeRepository.getRandom().collectLatest {
                _knowledgeEntity.value = it
            }
        }
    }
}