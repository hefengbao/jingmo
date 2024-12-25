/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class WritingIndexViewModel @Inject constructor(
    val json: Json,
    private val writingRepository: WritingRepository
) : ViewModel() {
    private var maxId = 1000000

    init {
        viewModelScope.launch {
            writingRepository.getMaxId().collectLatest {
                maxId = if (it == 0) 1 else it
                getRandomWriting()
            }
        }
    }

    private val _writing: MutableStateFlow<WritingEntity?> = MutableStateFlow(null)
    val writing: SharedFlow<WritingEntity?> = _writing
    fun getRandomWriting() {

        viewModelScope.launch {
            writingRepository.get(
                (1..maxId).random()
            ).collectLatest {
                _writing.value = it
            }
        }
    }

    private val _collected: MutableStateFlow<WritingCollectionEntity?> = MutableStateFlow(null)
    val collected: SharedFlow<WritingCollectionEntity?> = _collected
    fun getCollected(id: Int) {
        viewModelScope.launch {
            writingRepository.isCollect(id).collectLatest {
                _collected.value = it
            }
        }
    }

    fun setUncollect(id: Int) {
        viewModelScope.launch {
            writingRepository.uncollect(id)
        }
    }

    fun setCollect(id: Int) {
        viewModelScope.launch {
            writingRepository.collect(WritingCollectionEntity(id))
        }
    }
}