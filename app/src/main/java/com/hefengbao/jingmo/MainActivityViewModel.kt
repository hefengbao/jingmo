package com.hefengbao.jingmo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.DataStatus
import com.hefengbao.jingmo.data.model.toChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.toIdiomEntity
import com.hefengbao.jingmo.data.model.toPoemEntity
import com.hefengbao.jingmo.data.model.toPoemSentenceEntity
import com.hefengbao.jingmo.data.model.toPoemTagEntity
import com.hefengbao.jingmo.data.model.toTagEntity
import com.hefengbao.jingmo.data.model.toWriterEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.SyncRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val syncRepository: SyncRepository,
) : ViewModel() {
    private val _poemSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var poemSynced: SharedFlow<Boolean> = _poemSynced

    private val _tagSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var tagSynced: SharedFlow<Boolean> = _tagSynced

    private val _poemTagSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var poemTagSynced: SharedFlow<Boolean> = _poemTagSynced

    private val _writerSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var writerSynced: SharedFlow<Boolean> = _writerSynced

    private val _idiomSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var idiomSynced: SharedFlow<Boolean> = _idiomSynced

    private val _poemSentenceSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var poemSentenceSynced: SharedFlow<Boolean> = _poemSentenceSynced

    private val _chineseWiseCrackSynced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var chineseWisecrackSynced: SharedFlow<Boolean> = _chineseWiseCrackSynced

    var dataStatus: Flow<DataStatus> = preferenceRepository.getDataStatus()

    init {
        viewModelScope.launch {
            dataStatus.collectLatest {
                _poemSynced.value = it.poemSynced
                _tagSynced.value = it.tagSynced
                _poemTagSynced.value = it.poemTagSynced
                _writerSynced.value = it.writerSynced
                _idiomSynced.value = it.idiomSynced
                _chineseWiseCrackSynced.value = it.chineseWisecrackSynced
                _poemSentenceSynced.value = it.poemSentenceSynced
            }
        }
    }

    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    var showLanding: SharedFlow<Boolean> = _showLanding

    fun closeLanding() {
        viewModelScope.launch {
            delay(1000)
            _showLanding.value = false
        }
    }

    private val _poemProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemProgress: SharedFlow<Float> = _poemProgress

    private val _tagProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val tagProgress: SharedFlow<Float> = _tagProgress

    private val _poemTagProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemTagProgress: SharedFlow<Float> = _poemTagProgress

    private val _writerProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val writerProgress: SharedFlow<Float> = _writerProgress

    private val _poemSentenceProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemSentenceProgress: SharedFlow<Float> = _poemSentenceProgress

    private val _idiomProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val idiomProgress: SharedFlow<Float> = _idiomProgress

    private val _chineseWisecrackProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseWisecrackProgress: SharedFlow<Float> = _chineseWisecrackProgress

    fun sync() {
        viewModelScope.launch {
            if (!_poemSynced.value) {
                var count: Long = 0
                syncRepository.syncPoems().collectLatest {
                    it.map { poem ->
                        syncRepository.insertPoem(poem.toPoemEntity())
                        count++
                        _poemProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setPoemSyncedAndCount(true, count)
                _poemSynced.value = true
            }

            if (!_tagSynced.value) {
                var count: Long = 0
                syncRepository.syncTags().collectLatest {
                    it.map { tag ->
                        syncRepository.insertTag(tag.toTagEntity())
                        count++
                        _tagProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setTagSyncedAndCount(true, count)
                _tagSynced.value = true
            }

            if (!_poemTagSynced.value) {
                var count: Long = 0
                syncRepository.syncPoemTagList().collectLatest {
                    it.map { poemTag ->
                        syncRepository.insertPoemTag(poemTag.toPoemTagEntity())
                        count++
                        _poemTagProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setPoemTagSyncedAndCount(true, count)
                _poemTagSynced.value = true
            }

            if (!_writerSynced.value) {
                var count = 0L
                syncRepository.syncWriters().collectLatest {
                    it.map { writer ->
                        syncRepository.insertWriter(writer.toWriterEntity())
                        count++
                        _writerProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setWriterSyncedAndCount(true, count)
                _writerSynced.value = true
            }

            if (!_poemSentenceSynced.value) {
                var count: Long = 0
                syncRepository.syncPoemSentences().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertPoemSentence(sentence.toPoemSentenceEntity())
                        count++
                        _poemSentenceProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setPoemSentenceSyncedAndCount(true, count)
                _poemSentenceSynced.value = true
            }

            if (!_idiomSynced.value) {
                var count: Long = 0
                syncRepository.syncIdioms().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertIdiom(sentence.toIdiomEntity())
                        count++
                        _idiomProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setIdiomSyncedAndCount(true, count)
                _idiomSynced.value = true
            }

            if (!_chineseWiseCrackSynced.value) {
                var count: Long = 0
                syncRepository.syncChineseWisecracks().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertChineseWisecrack(sentence.toChineseWisecrackEntity())
                        count++
                        _chineseWisecrackProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setChineseWisecrackSyncedAndCount(true, count)
                _chineseWiseCrackSynced.value = true
            }
        }
    }
}