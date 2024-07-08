/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.asChineseExpressionEntity
import com.hefengbao.jingmo.data.model.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.asDictionaryEntity
import com.hefengbao.jingmo.data.model.asIdiomEntity
import com.hefengbao.jingmo.data.model.asPeopleEntity
import com.hefengbao.jingmo.data.model.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.asRiddleEntity
import com.hefengbao.jingmo.data.model.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.asWritingEntity
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepository
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository,
    private val preference: PreferenceRepository,
) : ViewModel() {
    val datasetPref = preference.getDatasetStatus()

    private val _datasetResult: MutableStateFlow<Result<List<Dataset>>> =
        MutableStateFlow(Result.Loading)
    val datasetResult: SharedFlow<Result<List<Dataset>>> = _datasetResult
    fun getDataset() {
        viewModelScope.launch {
            _datasetResult.value = repository.dataset()
        }
    }

    private val _chineseExpressionResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseExpressionResult: SharedFlow<SyncStatus<Any>> = _chineseExpressionResult
    private val _chineseExpressionResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseExpressionResultProgress: SharedFlow<Float> = _chineseExpressionResultProgress
    fun syncChineseExpression(total: Int, version: Int) {
        _chineseExpressionResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseExpression(page)) {
                    is Result.Error -> {
                        _chineseExpressionResult.value = SyncStatus.Error(response.exception)
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertChineseExpression(it.asChineseExpressionEntity())
                            count++
                            _chineseExpressionResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setChineseExpressionVersion(version)
            _chineseExpressionResult.value = SyncStatus.Success
        }
    }

    private val _chineseKnowledgeResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseKnowledgeResult: SharedFlow<SyncStatus<Any>> = _chineseKnowledgeResult
    private val _chineseKnowledgeResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseKnowledgeResultProgress: SharedFlow<Float> = _chineseKnowledgeResultProgress
    fun syncChineseKnowledge(total: Int, version: Int) {
        _chineseKnowledgeResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseKnowledge()) {
                is Result.Error -> _chineseKnowledgeResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChinesKnowledge(it.asChineseKnowledgeEntity())
                        count++
                        _chineseKnowledgeResultProgress.value = count.toFloat() / total
                    }

                    preference.setChineseKnowledgeVersion(version)
                    _chineseKnowledgeResult.value = SyncStatus.Success
                }
            }
        }
    }


    private val _chineseWisecracksResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseWisecracksResult: SharedFlow<SyncStatus<Any>> = _chineseWisecracksResult
    private val _chineseWisecracksResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseWisecracksResultProgress: SharedFlow<Float> = _chineseWisecracksResultProgress
    fun syncChineseWisecracks(total: Int, version: Int) {
        _chineseWisecracksResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseWisecracks()) {
                is Result.Error -> _chineseWisecracksResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseWisecrack(it.asChineseWisecrackEntity())
                        count++
                        _chineseWisecracksResultProgress.value = count.toFloat() / total
                    }

                    preference.setChineseWisecracksVersion(version)
                    _chineseWisecracksResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _dictionaryResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val dictionaryResult: SharedFlow<SyncStatus<Any>> = _dictionaryResult
    private val _dictionaryResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val dictionaryResultProgress: SharedFlow<Float> = _dictionaryResultProgress
    fun syncDictionary(total: Int, version: Int) {
        viewModelScope.launch {
            repository.clearDictionaryPinyin()
        }
        _dictionaryResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncDictionary(page)) {
                    is Result.Error -> _dictionaryResult.value =
                        SyncStatus.Error(response.exception)

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertDictionary(it.asDictionaryEntity())
                            it.pinyin2?.map { pinyin ->
                                repository.insertDictionaryPinyin(
                                    DictionaryPinyinEntity(
                                        dictionaryId = it.id,
                                        pinyin = pinyin,
                                    )
                                )
                            }
                            count++
                            _dictionaryResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setDictionaryVersion(version)
            _dictionaryResult.value = SyncStatus.Success
        }
    }

    private val _idiomsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val idiomsResult: SharedFlow<SyncStatus<Any>> = _idiomsResult
    private val _idiomsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val idiomsResultProgress: SharedFlow<Float> = _idiomsResultProgress
    fun syncIdioms(total: Int, version: Int) {
        _idiomsResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0

            while (page != null) {
                when (val response = repository.syncIdioms(page)) {
                    is Result.Error -> {
                        _idiomsResult.value = SyncStatus.Error(response.exception)
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }

                        response.data.data.map {
                            repository.insertIdiom(it.asIdiomEntity())
                            count++
                            _idiomsResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }

            // TODO 这里需优化
            preference.setIdiomsVersion(version)
            _idiomsResult.value = SyncStatus.Success
        }
    }

    private val _peopleResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val peopleResult: SharedFlow<SyncStatus<Any>> = _peopleResult
    private val _peopleResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val peopleResultProgress: SharedFlow<Float> = _peopleResultProgress
    fun syncPeople(total: Int, version: Int) {
        _peopleResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncPeople(page)) {
                    is Result.Error -> _peopleResult.value = SyncStatus.Error(response.exception)
                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertPeople(it.asPeopleEntity())
                            count++
                            _peopleResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setPeopleVersion(version)
            _peopleResult.value = SyncStatus.Success
        }
    }

    private val _classicPoemsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicPoemsResult: SharedFlow<SyncStatus<Any>> = _classicPoemsResult
    private val _classicPoemsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val classicPoemsResultProgress: SharedFlow<Float> = _classicPoemsResultProgress
    fun syncClassicPoems(total: Int, version: Int) {
        _classicPoemsResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncClassicPoems()) {
                is Result.Error -> {
                    _classicPoemsResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertClassicPoems(it.asClassicPoemEntity())
                        count++
                        _classicPoemsResultProgress.value = count.toFloat() / total
                    }

                    preference.setClassicPoemsVersion(version)
                    _classicPoemsResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _poemSentencesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val poemSentencesResult: SharedFlow<SyncStatus<Any>> = _poemSentencesResult
    private val _poemSentencesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemSentencesResultProgress: SharedFlow<Float> = _poemSentencesResultProgress
    fun syncPoemSentences(total: Int, version: Int) {
        _poemSentencesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncPoemSentences()) {
                is Result.Error -> {
                    _poemSentencesResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertPoemSentence(it.asPoemSentenceEntity())
                        count++
                        _poemSentencesResultProgress.value = count.toFloat() / total
                    }

                    preference.setPoemSentencesVersion(version)
                    _poemSentencesResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _riddlesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val riddlesResult: SharedFlow<SyncStatus<Any>> = _riddlesResult
    private val _riddlesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val riddlesResultProgress: SharedFlow<Float> = _riddlesResultProgress
    fun syncRiddles(total: Int, version: Int) {
        _riddlesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncRiddles()) {
                is Result.Error -> _riddlesResult.value = SyncStatus.Error(response.exception)
                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertRiddle(it.asRiddleEntity())
                        count++
                        _riddlesResultProgress.value = count.toFloat() / total
                    }

                    preference.setRiddlesVersion(version)
                    _riddlesResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _tongueTwistersResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val tongueTwisterResult: SharedFlow<SyncStatus<Any>> = _tongueTwistersResult
    private val _tongueTwistersResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val tongueTwistersResultProgress: SharedFlow<Float> = _tongueTwistersResultProgress
    fun syncTongueTwisters(total: Int, version: Int) {
        _tongueTwistersResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncTongueTwisters()) {
                is Result.Error -> _tongueTwistersResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertTongueTwister(it.asTongueTwisterEntity())
                        count++
                        _tongueTwistersResultProgress.value = count.toFloat() / total
                    }

                    preference.setTongueTwistersVersion(version)
                    _tongueTwistersResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _writingsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val writingsResult: SharedFlow<SyncStatus<Any>> = _writingsResult
    private val _writingsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val writingsResultProgress: SharedFlow<Float> = _writingsResultProgress

    private var writingCurrentPage: MutableStateFlow<Int> = MutableStateFlow(1)
    private var writingCurrentCount: MutableStateFlow<Int> = MutableStateFlow(0)
    fun setWritingsPreviousPage(page: Int) {
        writingCurrentPage.value = page
    }

    fun setWritingsPreviousCount(count: Int, total: Int) {
        writingCurrentCount.value = count
        _writingsResultProgress.value =
            writingCurrentCount.value.toFloat() / total
    }

    fun syncWritings(total: Int, version: Int) {
        _writingsResult.value = SyncStatus.Loading

        viewModelScope.launch {

            while (writingCurrentPage.value != 0) {
                when (val response = repository.syncWritings(writingCurrentPage.value)) {
                    is Result.Error -> _writingsResult.value == SyncStatus.Error(response.exception)
                    Result.Loading -> {}
                    is Result.Success -> {
                        response.data.data.map {
                            repository.insertWriting(it.asWritingEntity())
                            writingCurrentCount.value++
                            _writingsResultProgress.value =
                                writingCurrentCount.value.toFloat() / total
                        }
                        // 记录进度
                        preference.setWritingsCurrentPage(writingCurrentPage.value)
                        preference.setWritingsCurrentCount(writingCurrentCount.value)

                        if (response.data.nextPage != null) {
                            writingCurrentPage.value++
                        } else {
                            writingCurrentPage.value = 0
                        }

                    }
                }
            }
            preference.setWritingsVersion(version)
            preference.setWritingsCurrentPage(1)
            preference.setWritingsCurrentCount(0)
            _writingsResult.value = SyncStatus.Success
        }
    }
}

sealed interface SyncStatus<out T> {
    data class Error(val exception: Throwable? = null) : SyncStatus<Nothing>
    data object Success : SyncStatus<Nothing>
    data object Loading : SyncStatus<Nothing>
    data object NonStatus : SyncStatus<Nothing>
}