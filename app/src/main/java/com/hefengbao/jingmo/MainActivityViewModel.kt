package com.hefengbao.jingmo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.DataSetVersion
import com.hefengbao.jingmo.data.model.DataStatus
import com.hefengbao.jingmo.data.model.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.asIdiomEntity
import com.hefengbao.jingmo.data.model.asPoemEntity
import com.hefengbao.jingmo.data.model.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.asPoemTagEntity
import com.hefengbao.jingmo.data.model.asTagEntity
import com.hefengbao.jingmo.data.model.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.asWriterEntity
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
    private val _synced: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val synced: SharedFlow<Boolean> = _synced

    private val _poemVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val poemVersion: SharedFlow<Int> = _poemVersion

    private val _tagVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val tagVersion: SharedFlow<Int> = _tagVersion

    private val _poemTagVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val poemTagVersion: SharedFlow<Int> = _poemTagVersion

    private val _writerVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val writerVersion: SharedFlow<Int> = _writerVersion

    private val _idiomVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val idiomVersion: SharedFlow<Int> = _idiomVersion

    private val _poemSentenceVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val poemSentenceVersion: SharedFlow<Int> = _poemSentenceVersion

    private val _chineseWiseCrackVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val chineseWiseCrackVersion: SharedFlow<Int> = _chineseWiseCrackVersion

    private val _tongueTwisterVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val tongueTwisterVersion: SharedFlow<Int> = _tongueTwisterVersion

    private val _chineseKnowledgeVersion: MutableStateFlow<Int> = MutableStateFlow(0)
    val chineseKnowledgeVersion: SharedFlow<Int> = _chineseKnowledgeVersion

    private val dataStatus: Flow<DataStatus> = preferenceRepository.getDataStatus()

    init {
        viewModelScope.launch {
            dataStatus.collectLatest {
                _poemVersion.value = it.poemVersion
                _tagVersion.value = it.tagVersion
                _poemTagVersion.value = it.poemTagVersion
                _writerVersion.value = it.writerVersion
                _idiomVersion.value = it.idiomVersion
                _chineseWiseCrackVersion.value = it.chineseWisecrackVersion
                _poemSentenceVersion.value = it.poemSentenceVersion
                _tongueTwisterVersion.value = it.tongueTwisterVersion
                _chineseKnowledgeVersion.value = it.chineseKnowledgeVersion

                _synced.value = it.allSynced
            }
        }
    }

    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    var showLanding: SharedFlow<Boolean> = _showLanding

    fun closeLanding() {
        viewModelScope.launch {
            delay(1500)
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

    private val _tongueTwisterProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val tongueTwisterProgress: SharedFlow<Float> = _tongueTwisterProgress

    private val _chineseKnowledgeProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseKnowledgeProgress: SharedFlow<Float> = _chineseKnowledgeProgress

    fun sync() {
        viewModelScope.launch {
            if (_poemVersion.value != DataSetVersion.poem) {
                var count: Long = 0
                syncRepository.syncPoems().collectLatest {
                    it.map { poem ->
                        syncRepository.insertPoem(poem.asPoemEntity())
                        count++
                        _poemProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setPoemVersion(DataSetVersion.poem)
                _poemVersion.value = DataSetVersion.poem
            }

            if (_tagVersion.value != DataSetVersion.tag) {
                var count: Long = 0
                syncRepository.syncTags().collectLatest {
                    it.map { tag ->
                        syncRepository.insertTag(tag.asTagEntity())
                        count++
                        _tagProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setTagVersion(DataSetVersion.tag)
                _tagVersion.value = DataSetVersion.tag
            }

            if (_poemTagVersion.value != DataSetVersion.poemTag) {
                var count: Long = 0
                syncRepository.syncPoemTagList().collectLatest {
                    it.map { poemTag ->
                        syncRepository.insertPoemTag(poemTag.asPoemTagEntity())
                        count++
                        _poemTagProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setPoemTagVersion(DataSetVersion.poemTag)
                _poemTagVersion.value = DataSetVersion.poemTag
            }

            if (_writerVersion.value != DataSetVersion.writer) {
                var count = 0L
                syncRepository.syncWriters().collectLatest {
                    it.map { writer ->
                        syncRepository.insertWriter(writer.asWriterEntity())
                        count++
                        _writerProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setWriterVersion(DataSetVersion.writer)
                _writerVersion.value = DataSetVersion.writer
            }

            if (_poemSentenceVersion.value != DataSetVersion.poemSentence) {
                var count: Long = 0
                syncRepository.syncPoemSentences().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertPoemSentence(sentence.asPoemSentenceEntity())
                        count++
                        _poemSentenceProgress.value = count.toFloat() / it.size
                    }
                }
                //preferenceRepository.setPoemSentenceVersion(DataSetVersion.poemSentence)
                _poemSentenceVersion.value = DataSetVersion.poemSentence
            }

            if (_idiomVersion.value != DataSetVersion.idiom) {
                var count: Long = 0
                syncRepository.syncIdioms().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertIdiom(sentence.asIdiomEntity())
                        count++
                        _idiomProgress.value = count.toFloat() / it.size
                    }
                }
                //preferenceRepository.setIdiomVersion(DataSetVersion.idiom)
                _idiomVersion.value = DataSetVersion.idiom
            }

            if (_chineseWiseCrackVersion.value != DataSetVersion.chineseWisecrack) {
                var count: Long = 0
                syncRepository.syncChineseWisecracks().collectLatest {
                    it.map { sentence ->
                        syncRepository.insertChineseWisecrack(sentence.asChineseWisecrackEntity())
                        count++
                        _chineseWisecrackProgress.value = count.toFloat() / it.size
                    }
                }
                //preferenceRepository.setChineseWisecrackVersion(DataSetVersion.chineseWisecrack)
                _chineseWiseCrackVersion.value = DataSetVersion.chineseWisecrack
            }

            if (_tongueTwisterVersion.value != DataSetVersion.chineseWisecrack) {
                var count: Long = 0
                syncRepository.syncTongueTwisters().collectLatest {
                    it.map { tongueTwister ->
                        syncRepository.insertTongueTwister(tongueTwister.asTongueTwisterEntity())
                        count++
                        _tongueTwisterProgress.value = count.toFloat() / it.size
                    }
                }
                //preferenceRepository.setTongueTwisterVersion(DataSetVersion.tongueTwister)
                _tongueTwisterVersion.value = DataSetVersion.tongueTwister
            }

            if (_chineseKnowledgeVersion.value != DataSetVersion.chineseKnowledge) {
                var count: Long = 0
                syncRepository.syncChineseKnowledge().collectLatest {
                    it.map { chineseKnowledge ->
                        syncRepository.insertChineseKnowledge(chineseKnowledge.asChineseKnowledgeEntity())
                        count++
                        _chineseKnowledgeProgress.value = count.toFloat() / it.size
                    }
                }
                preferenceRepository.setChineseKnowledgeVersion(DataSetVersion.chineseKnowledge)
                _chineseKnowledgeVersion.value = DataSetVersion.chineseKnowledge
            }
        }
    }
}