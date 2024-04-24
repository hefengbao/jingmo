package com.hefengbao.jingmo.ui.screen.writing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import com.hefengbao.jingmo.data.repository.WritingRepository
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
    val writingRepository: WritingRepository
) : ViewModel() {
    init {
        getRandomWriting()
    }

    private val _writing: MutableStateFlow<WritingWithBookmark?> = MutableStateFlow(null)
    val writing: SharedFlow<WritingWithBookmark?> = _writing
    fun getRandomWriting() {
        viewModelScope.launch {
            // 由于这里是随机查询，点击“收藏”或“取消收藏”都会导致这里数据变化，产生和“刷新”相同的效果/(ㄒoㄒ)/~~
            writingRepository.random().collectLatest {
                _writing.value = it
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